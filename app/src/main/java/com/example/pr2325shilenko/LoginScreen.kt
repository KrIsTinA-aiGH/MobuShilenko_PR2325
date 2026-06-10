package com.example.pr2325shilenko

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    val isButtonEnabled = email.text.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Приветствие
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "👋",
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Добро пожаловать!",
                fontSize = 24.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Войдите, чтобы пользоваться функциями приложения",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Поле ввода Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Вход по E-mail", fontSize = 14.sp) },
            placeholder = { Text("example@mail.ru", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка "Далее"
        Button(
            onClick = onLoginClick,
            enabled = isButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2979FF),
                disabledContainerColor = Color(0xFFC5CAE9)
            )
        ) {
            Text("Далее", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Или войдите с помощью",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка "Войти с Яндекс"
        OutlinedButton(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black
            )
        ) {
            Text("Войти с Яндекс", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}