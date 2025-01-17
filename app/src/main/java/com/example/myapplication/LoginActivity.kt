package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize views
        val emailEditText: EditText = findViewById(R.id.etEmail)
        val passwordEditText: EditText = findViewById(R.id.etPassword)
        val loginButton: Button = findViewById(R.id.btnNext)
        val forgotPasswordText: TextView = findViewById(R.id.tvForgotPassword)

        // Handle login button click
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your Email and Password", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Welcome back, $email!", Toast.LENGTH_SHORT).show()
            }
        }

        val btnLogin = findViewById<Button>(R.id.btnNext)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

//        btnLogin.setOnClickListener {
//            // Show login success message (you can customize this)
//            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
//        }
        btnRegister.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            registerIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Prevent looping
            startActivity(registerIntent)
            finish() // Finish the login activity so the user can't navigate back to it
        }

        // Handle forgot password click
        forgotPasswordText.setOnClickListener {
            Toast.makeText(this, "Forgot Password clicked", Toast.LENGTH_SHORT).show()
        }
    }
}