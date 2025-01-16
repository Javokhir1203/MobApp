package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText: EditText = findViewById(R.id.etEmail)
        val passwordEditText: EditText = findViewById(R.id.etPassword)
        val rememberMeCheckBox: CheckBox = findViewById(R.id.cbRememberMe)
        val forgotPasswordText: TextView = findViewById(R.id.tvForgotPassword)
        val nextButton: Button = findViewById(R.id.btnNext)

        nextButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your Email and Password", Toast.LENGTH_SHORT)
                    .show()
            }else{
                Toast.makeText(this, "Welcome back, $email!", Toast.LENGTH_SHORT).show()
            }
        }
        forgotPasswordText.setOnClickListener {
            Toast.makeText(this, "Forgot Password clicked", Toast.LENGTH_SHORT).show()
        }
    }
    }