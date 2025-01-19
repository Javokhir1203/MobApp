package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    // Lazy initialization for views
    private val emailInputLayout: TextInputLayout by lazy { findViewById(R.id.textInputLayoutEmail) }
    private val emailEditText: EditText by lazy { findViewById(R.id.etEmail) }
    private val passwordInputLayout: TextInputLayout by lazy { findViewById(R.id.textInputLayoutPassword) }
    private val passwordEditText: EditText by lazy { findViewById(R.id.etPassword) }
    private val loginButton: Button by lazy { findViewById(R.id.btnNext) }
    private val forgotPasswordText: TextView by lazy { findViewById(R.id.tvForgotPassword) }
    private val registerButton: Button by lazy { findViewById(R.id.btnRegister) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener { handleLogin() }
        registerButton.setOnClickListener { navigateToRegister() }
        forgotPasswordText.setOnClickListener { handleForgotPassword() }
    }

    private fun handleLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Validate input
        if (email.isEmpty()) {
            emailInputLayout.error = "Email cannot be empty"
        } else if (!CredentialsManager.isEmailValid(email)) {
            emailInputLayout.error = "Invalid email format"
        } else {
            emailInputLayout.error = null
        }

        if (password.isEmpty()) {
            passwordInputLayout.error = "Password cannot be empty"
        } else if (!CredentialsManager.isValidPassword(password)) {
            passwordInputLayout.error = "Password must be at least 8 characters long"
        } else {
            passwordInputLayout.error = null
        }

        // Check for hardcoded credentials
        if (emailInputLayout.error == null && passwordInputLayout.error == null) {
            if (CredentialsManager.isHardcodedCredentials(email, password)) {
                navigateToMainActivity()
            } else {
                Toast.makeText(this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToRegister() {
        val registerIntent = Intent(this, RegisterActivity::class.java)
        registerIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(registerIntent)
        finish()
    }

    private fun handleForgotPassword() {
        Toast.makeText(this, "Forgot Password clicked", Toast.LENGTH_SHORT).show()
    }
}
