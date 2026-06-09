package com.example.pr2325shilenko

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // НЕ вызываем setContentView()! View берется из темы

        // Сразу переходим к MainActivity
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}