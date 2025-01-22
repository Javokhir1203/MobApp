package com.example.myapplication

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CredentialsManager(private val context: Context) {

    private val userAccounts: MutableMap<String, String> = mutableMapOf()

    init {
        loadUserAccounts()
    }

    companion object {
        // Static methods for testing purposes
        fun isValidEmail(email: String): Boolean {
            val emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}"
            return email.isNotEmpty() && email.matches(emailPattern.toRegex())
        }

        fun isValidPassword(password: String): Boolean {
            return password.length >= 8 && password.any { it.isUpperCase() } && password.any { it.isDigit() }
        }
    }

    // Helper method for normalizing email
    private fun normalizeEmail(email: String): String = email.trim().lowercase()

    // Load user accounts from shared preferences
    private fun loadUserAccounts() {
        val sharedPreferences = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
        val gson = Gson()
        val accountsJson = sharedPreferences.getString("user_accounts", null)
        userAccounts.putAll(
            if (accountsJson != null) {
                val type = object : TypeToken<MutableMap<String, String>>() {}.type
                gson.fromJson(accountsJson, type)
            } else {
                mutableMapOf()
            }
        )
    }

    // Save user accounts to shared preferences
    private fun saveUserAccounts() {
        val sharedPreferences = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
        val gson = Gson()
        val accountsJson = gson.toJson(userAccounts)
        sharedPreferences.edit().putString("user_accounts", accountsJson).apply()
    }

    // Register a new account with the given email and password
    fun registerAccount(email: String, password: String): Boolean {
        val normalizedEmail = normalizeEmail(email)
        if (userAccounts.containsKey(normalizedEmail)) {
            return false
        }
        userAccounts[normalizedEmail] = password
        saveUserAccounts()
        return true
    }

    // Validate login credentials
    fun isValidLogin(email: String, password: String): Boolean {
        val normalizedEmail = normalizeEmail(email)
        return userAccounts[normalizedEmail] == password || isValidAdminLogin(email, password)
    }

    fun isValidAdminLogin(email: String, password: String): Boolean {
        val normalizedEmail = normalizeEmail(email)
        return normalizedEmail == "test@te.st" && password == "1234"
    }

    // Check if an email is available for registration
    fun isEmailAvailable(email: String): Boolean {
        val normalizedEmail = normalizeEmail(email)
        return !userAccounts.containsKey(normalizedEmail)
    }

    // Validate credentials for login
    fun areCredentialsValid(email: String, password: String): Boolean {
        return isValidLogin(email, password)
    }
}
