package com.sample.composenav3.ui.auth

object AuthConstants {

    const val MIN_PASSWORD_LENGTH = 6

    // Common labels
    const val LABEL_EMAIL = "Email"
    const val LABEL_PASSWORD = "Password"
    const val LABEL_NAME = "Name"
    const val LABEL_CONFIRM_PASSWORD = "Confirm Password"

    // Login screen
    const val LOGIN_TITLE = "Welcome Back"
    const val LOGIN_SUBTITLE = "Sign in to continue"
    const val LOGIN_BUTTON = "Login"
    const val LOGIN_NO_ACCOUNT = "Don't have an account?"
    const val LOGIN_REGISTER_LINK = "Register"

    // Register screen
    const val REGISTER_TITLE = "Create Account"
    const val REGISTER_SUBTITLE = "Sign up to get started"
    const val REGISTER_BUTTON = "Register"
    const val REGISTER_HAS_ACCOUNT = "Already have an account?"
    const val REGISTER_LOGIN_LINK = "Login"

    // Content descriptions
    const val CD_HIDE_PASSWORD = "Hide password"
    const val CD_SHOW_PASSWORD = "Show password"

    // Validation errors
    const val ERROR_EMAIL_REQUIRED = "Email is required"
    const val ERROR_EMAIL_INVALID = "Invalid email format"
    const val ERROR_PASSWORD_REQUIRED = "Password is required"
    const val ERROR_PASSWORD_MIN_LENGTH = "Password must be at least $MIN_PASSWORD_LENGTH characters"
    const val ERROR_NAME_REQUIRED = "Name is required"
    const val ERROR_CONFIRM_PASSWORD_REQUIRED = "Please confirm your password"
    const val ERROR_PASSWORDS_NOT_MATCH = "Passwords do not match"
}
