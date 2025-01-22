package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        credentialsManager = CredentialsManager(this)

        if (savedInstanceState == null) {
            showLoginFragment() // Show the login fragment by default
        }
    }

    // Method to show the LoginFragment
    fun showLoginFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, LoginFragment())
        transaction.addToBackStack(null) // Add to back stack to allow navigation
        transaction.commit()
    }

    // Method to show the RegistrationFragment
    fun showRegistrationFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, RegistrationFragment())
        transaction.addToBackStack(null) // Add to back stack to allow navigation backwards
        transaction.commit()
    }

    fun showRecyclerViewFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, RecyclerViewFragment())
        transaction.addToBackStack(null) // Allow back navigation
        transaction.commit()
    }


    // Optional method to handle fragment back navigation manually
    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment is LoginFragment) {
            super.onBackPressed() // Let the system handle the back press
        } else if (fragment is RegistrationFragment) {
            showLoginFragment() // If in registration, go back to login without popping the back stack
        }
    }
}
