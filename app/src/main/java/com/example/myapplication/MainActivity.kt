package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize buttons
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnLogin = findViewById<Button>(R.id.btnNext)

        btnLogin.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Prevent looping
            startActivity(loginIntent)
            finish() // Finish current activity so the user can't go back to it
        }


        // Navigate to Register Activity
        btnRegister.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            registerIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Prevent looping
            startActivity(registerIntent)
            finish() // Finish current activity so the user can't go back to it
        }
    }
}