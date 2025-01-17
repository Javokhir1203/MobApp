package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize views
        val fullNameEditText: EditText = findViewById(R.id.et_full_name)
        val emailEditText: EditText = findViewById(R.id.et_email)
        val phoneEditText: EditText = findViewById(R.id.et_phone_number)
        val passwordEditText: EditText = findViewById(R.id.et_password)
        val registerButton: Button = findViewById(R.id.btnN)

        // Handle register button click
        registerButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Account created for $fullName!", Toast.LENGTH_SHORT).show()
            }
        }
        val btnBackToLogin = findViewById<Button>(R.id.button2)

        btnBackToLogin.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Prevent looping
            startActivity(loginIntent)
            finish() // Finish current activity so the user can't navigate back to RegisterActivity
        }
    }
}
