
package com.example.myapplication

object CredentialsManager {

    private val emailPattern = ("[a-zA-Z0-9\\+\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][0-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][0-zA-Z0-9\\-]{0,25}" +
            ")+").toRegex()

    private val registeredUsers = mutableMapOf<String, String>()

    fun isEmailValid(email: String): Boolean {
        return email.matches(emailPattern)
    }

    fun isEmailAlreadyUsed(email: String): Boolean {
        return registeredUsers.containsKey(email.lowercase())
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    fun register(email: String, password: String): Result<String> {
        val normalizedEmail = email.lowercase()

        return when {
            !isEmailValid(email) -> Result.failure(Exception("Invalid email format"))
            !isValidPassword(password) -> Result.failure(Exception("Password must be at least 8 characters long"))
            registeredUsers.containsKey(normalizedEmail) -> Result.failure(Exception("Email is already taken"))
            else -> {
                registeredUsers[normalizedEmail] = password
                Result.success("Registration successful")
            }
        }
    }


    fun validateLogin(email: String, password: String): Boolean {
        return registeredUsers[email.lowercase()] == password
    }

    fun validateCredentials(email: String, password: String, termsAccepted: Boolean): Boolean {
        val isEmailValid = email.matches(Regex("[a-zA-Z0-9+%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
        val isPasswordValid = password.length >= 8
        println("Email Valid: $isEmailValid, Password Valid: $isPasswordValid, Terms Accepted: $termsAccepted")
        return isEmailValid && isPasswordValid && termsAccepted
    }

    fun isValidFullName(fullName: String): Boolean {
        return fullName.isNotEmpty()
    }

    fun isHardcodedCredentials(email: String, password: String): Boolean {
        val hardcodedEmail = "test@te.st"
        val hardcodedPassword = "1234"
        return email == hardcodedEmail && password == hardcodedPassword
    }

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val phonePattern = "^[0-9]{9,}$".toRegex()
        return phoneNumber.matches(phonePattern)
    }

    fun isTermsAccepted(isChecked: Boolean): Boolean {
        return isChecked
    }

    fun validateCredentialsForSignUp(
        fullName: String,
        email: String,
        phoneNumber: String,
        password: String,
        isTermsAccepted: Boolean
    ): Boolean {
        return isValidFullName(fullName) &&
                isEmailValid(email) &&
                isValidPhoneNumber(phoneNumber) &&
                isValidPassword(password) &&
                isTermsAccepted(isTermsAccepted)
    }
}