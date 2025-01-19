package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private val fullNameInputLayout: TextInputLayout by lazy { findViewById(R.id.textInputLayoutFullName) }
    private val emailInputLayout: TextInputLayout by lazy { findViewById(R.id.textInputLayoutEmail) }
    private val passwordInputLayout: TextInputLayout by lazy { findViewById(R.id.textInputLayoutPassword) }
    private val registerButton by lazy { findViewById<Button>(R.id.btnN) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton.setOnClickListener { handleRegistration() }
    }

    private fun handleRegistration() {
        val fullName = fullNameInputLayout.editText?.text.toString().trim()
        val email = emailInputLayout.editText?.text.toString().trim()
        val password = passwordInputLayout.editText?.text.toString().trim()

        if (fullName.isEmpty()) {
            fullNameInputLayout.error = "Full name cannot be empty"
            return
        } else {
            fullNameInputLayout.error = null
        }

        val registrationResult = CredentialsManager.register(email, password)
        if (registrationResult.isSuccess) {
            Toast.makeText(this, "Account created for $fullName!", Toast.LENGTH_SHORT).show()
            navigateToLogin()
        } else {
            val errorMessage = registrationResult.exceptionOrNull()?.message ?: "Unknown error"
            if (errorMessage.contains("Email")) {
                emailInputLayout.error = errorMessage
            } else if (errorMessage.contains("Password")) {
                passwordInputLayout.error = errorMessage
            }
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
