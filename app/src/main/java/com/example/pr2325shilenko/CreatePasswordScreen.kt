package com.example.pr2325shilenko

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreatePasswordScreen(onPasswordCreated: () -> Unit) {
    var password by remember { mutableStateOf(List(4) { "" }) }
    val isPasswordComplete = password.all { it.isNotEmpty() }

    LaunchedEffect(isPasswordComplete) {
        if (isPasswordComplete) {
            kotlinx.coroutines.delay(300)
            onPasswordCreated()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Пропустить",
                color = Color(0xFF4A90E2),
                fontSize = 16.sp,
                modifier = Modifier.clickable { onPasswordCreated() }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Создайте пароль",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Для защиты ваших персональных данных",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Spacer(modifier = Modifier.height(40.dp))

// Точки по центру экрана
        Row(
            horizontalArrangement = Arrangement.Center, // <-- Центрируем
            modifier = Modifier.fillMaxWidth() // <-- На всю ширину
        ) {
            password.forEachIndexed { index, digit ->
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(horizontal = 8.dp) // <-- Отступы между точками
                        .clip(CircleShape)
                        .background(
                            if (digit.isNotEmpty()) Color(0xFF2979FF)
                            else Color(0xFFE0E0E0)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(3) { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    repeat(3) { col ->
                        val number = row * 3 + col + 1
                        NumberButton(
                            number = number.toString(),
                            onClick = {
                                val emptyIndex = password.indexOfFirst { it.isEmpty() }
                                if (emptyIndex != -1) {
                                    val newPassword = password.toMutableList()
                                    newPassword[emptyIndex] = number.toString()
                                    password = newPassword
                                }
                            }
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                Box(modifier = Modifier.size(70.dp))

                NumberButton(
                    number = "0",
                    onClick = {
                        val emptyIndex = password.indexOfFirst { it.isEmpty() }
                        if (emptyIndex != -1) {
                            val newPassword = password.toMutableList()
                            newPassword[emptyIndex] = "0"
                            password = newPassword
                        }
                    }
                )

                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .clickable {
                            val lastIndex = password.indexOfLast { it.isNotEmpty() }
                            if (lastIndex != -1) {
                                val newPassword = password.toMutableList()
                                newPassword[lastIndex] = ""
                                password = newPassword
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "⌫",
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun NumberButton(number: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(Color(0xFFF5F5F5))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = number,
                fontSize = 28.sp,
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
                Text(
                    text = letters,
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
        }
    }
}