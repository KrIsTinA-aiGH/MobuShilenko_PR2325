package com.example.pr2325shilenko

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun EmailCodeScreen(onCodeVerified: () -> Unit) {
    val codeLength = 4
    var code by remember { mutableStateOf(List(codeLength) { "" }) }
    val focusRequesters = remember { List(codeLength) { FocusRequester() } }

    // Состояние для таймера
    var timeLeft by remember { mutableStateOf(60) }
    var isTimerRunning by remember { mutableStateOf(true) }

    // Генерация случайного кода
    var verificationCode by remember { mutableStateOf((1000..9999).random()) }
    val context = LocalContext.current

    // Показываем код при входе и при повторной отправке
    LaunchedEffect(verificationCode) {
        println("🔐 Ваш код: $verificationCode")
        Toast.makeText(context, "Код для проверки: $verificationCode", Toast.LENGTH_LONG).show()
    }

    // Таймер обратного отсчета
    LaunchedEffect(isTimerRunning, timeLeft) {
        if (isTimerRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else if (timeLeft == 0) {
            isTimerRunning = false
        }
    }

    // Проверка кода
    val isCodeComplete = code.all { it.isNotEmpty() }
    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(isCodeComplete) {
        if (isCodeComplete) {
            val enteredCode = code.joinToString("")
            if (enteredCode == verificationCode.toString()) {
                delay(300)
                onCodeVerified()
            } else {
                showError = true
                Toast.makeText(context, "Неверный код! Попробуйте ещё раз", Toast.LENGTH_SHORT).show()
                delay(1500)
                showError = false
                code = List(codeLength) { "" }
                focusRequesters[0].requestFocus()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Введите код из E-mail",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Поля для ввода кода
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            code.forEachIndexed { index, digit ->
                OutlinedTextField(
                    value = digit,
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                            val newCode = code.toMutableList()
                            newCode[index] = newValue
                            code = newCode

                            if (newValue.isNotEmpty() && index < codeLength - 1) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .focusRequester(focusRequesters[index]),
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Таймер и кнопка повторной отправки
        if (isTimerRunning) {
            Text(
                text = "Отправить код повторно можно будет через ${timeLeft}с",
                fontSize = 14.sp,
                color = Color.Gray
            )
        } else {
            TextButton(
                onClick = {
                    // Генерируем НОВЫЙ код
                    verificationCode = (1000..9999).random()
                    // Сбрасываем таймер
                    timeLeft = 60
                    isTimerRunning = true
                    // Очищаем поля
                    code = List(codeLength) { "" }
                    focusRequesters[0].requestFocus()
                }
            ) {
                Text(
                    text = "Отправить код повторно",
                    fontSize = 14.sp,
                    color = Color(0xFF2979FF)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Цифровая клавиатура
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(3) { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    repeat(3) { col ->
                        val number = row * 3 + col + 1
                        NumberKeyButton(
                            number = number.toString(),
                            onClick = {
                                val emptyIndex = code.indexOfFirst { it.isEmpty() }
                                if (emptyIndex != -1 && emptyIndex < codeLength) {
                                    val newCode = code.toMutableList()
                                    newCode[emptyIndex] = number.toString()
                                    code = newCode
                                    if (emptyIndex < codeLength - 1) {
                                        focusRequesters[emptyIndex + 1].requestFocus()
                                    }
                                }
                            }
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Box(modifier = Modifier.size(70.dp))

                NumberKeyButton(
                    number = "0",
                    onClick = {
                        val emptyIndex = code.indexOfFirst { it.isEmpty() }
                        if (emptyIndex != -1 && emptyIndex < codeLength) {
                            val newCode = code.toMutableList()
                            newCode[emptyIndex] = "0"
                            code = newCode
                            if (emptyIndex < codeLength - 1) {
                                focusRequesters[emptyIndex + 1].requestFocus()
                            }
                        }
                    }
                )

                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clickable {
                            val lastIndex = code.indexOfLast { it.isNotEmpty() }
                            if (lastIndex != -1) {
                                val newCode = code.toMutableList()
                                newCode[lastIndex] = ""
                                code = newCode
                                focusRequesters[lastIndex].requestFocus()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text("⌫", fontSize = 24.sp, color = Color.Black)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun NumberKeyButton(number: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .background(
                color = if (number.isEmpty()) Color.Transparent else Color(0xFFF5F5F5),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(enabled = number.isNotEmpty()) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (number.isNotEmpty()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = number,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                if (number.toIntOrNull() != null) {
                    val letters = when (number) {
                        "2" -> "ABC"
                        "3" -> "DEF"
                        "4" -> "GHI"
                        "5" -> "JKL"
                        "6" -> "MNO"
                        "7" -> "PQRS"
                        "8" -> "TUV"
                        "9" -> "WXYZ"
                        else -> ""
                    }
                    Text(text = letters, fontSize = 10.sp, color = Color.Gray)
                }
            }
        }
    }
}