package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class RegistrationFragment : Fragment(R.layout.registration_activity) {

    private lateinit var credentialsManager: CredentialsManager
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var loginNextTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        credentialsManager = (activity as MainActivity).credentialsManager
        emailEditText = view.findViewById(R.id.email_text)
        passwordEditText = view.findViewById(R.id.password_text)
        registerButton = view.findViewById(R.id.nextButton)
        loginNextTextView = view.findViewById(R.id.login_next_text_view)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validation logic for registration
            when {
                !CredentialsManager.isValidEmail(email) -> {
                    emailEditText.error = "Invalid email format"
                }
                !CredentialsManager.isValidPassword(password) -> {
                    passwordEditText.error = "Password must be at least 8 characters, with an uppercase letter and a digit"
                }
                !credentialsManager.isEmailAvailable(email) -> {
                    emailEditText.error = "Email already registered"
                }
                credentialsManager.registerAccount(email, password) -> {
                    (activity as MainActivity).showLoginFragment() // Go back to login fragment after registration
                }
                else -> {
                    emailEditText.error = "Email already registered"
                }
            }
        }

        loginNextTextView.setOnClickListener {
            (activity as MainActivity).showLoginFragment() // Go to login fragment
        }
    }
}

