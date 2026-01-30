package com.sample.composenav3.ui.auth.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.sample.composenav3.ui.auth.AuthConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isPasswordVisible: Boolean = false
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, emailError = null) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, passwordError = null) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun validateAndLogin(): Boolean {
        val state = _uiState.value
        var isValid = true

        val emailError = when {
            state.email.isBlank() -> {
                isValid = false
                AuthConstants.ERROR_EMAIL_REQUIRED
            }
            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> {
                isValid = false
                AuthConstants.ERROR_EMAIL_INVALID
            }
            else -> null
        }

        val passwordError = when {
            state.password.isBlank() -> {
                isValid = false
                AuthConstants.ERROR_PASSWORD_REQUIRED
            }
            state.password.length < AuthConstants.MIN_PASSWORD_LENGTH -> {
                isValid = false
                AuthConstants.ERROR_PASSWORD_MIN_LENGTH
            }
            else -> null
        }

        _uiState.update { it.copy(emailError = emailError, passwordError = passwordError) }
        return isValid
    }
}
