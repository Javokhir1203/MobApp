package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class LoginFragment : Fragment(R.layout.login_activity) {

    private lateinit var credentialsManager: CredentialsManager
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerNowTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        credentialsManager = (activity as MainActivity).credentialsManager
        emailEditText = view.findViewById(R.id.email_text)
        passwordEditText = view.findViewById(R.id.password_text)
        loginButton = view.findViewById(R.id.nextButton)
        registerNowTextView = view.findViewById(R.id.register_now_text_view)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validation logic for login
            when {
                !CredentialsManager.isValidEmail(email) -> {
                    emailEditText.error = "Invalid email format"
                }
                credentialsManager.isValidAdminLogin(email, password) -> {
                    navigateRecipeFragment()
                }
                !CredentialsManager.isValidPassword(password) -> {
                    passwordEditText.error = "Password must be at least 8 characters, with an uppercase letter and a digit"
                }
                credentialsManager.isValidLogin(email, password) -> {
                    navigateRecipeFragment()
                }
                else -> {
                    passwordEditText.error = "Incorrect credentials"
                }
            }
        }

        registerNowTextView.setOnClickListener {
            (activity as MainActivity).showRegistrationFragment() // Go to registration screen
        }
    }

    private fun navigateRecipeFragment() {
        (activity as MainActivity).showRecyclerViewFragment()
    }
}

