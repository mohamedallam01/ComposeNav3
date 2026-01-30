package com.sample.composenav3.ui.auth.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.sample.composenav3.ui.auth.AuthConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val nameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false
)

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name, nameError = null) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, emailError = null) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, passwordError = null) }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _uiState.update { it.copy(confirmPassword = confirmPassword, confirmPasswordError = null) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun toggleConfirmPasswordVisibility() {
        _uiState.update { it.copy(isConfirmPasswordVisible = !it.isConfirmPasswordVisible) }
    }

    fun validateAndRegister(): Boolean {
        val state = _uiState.value
        var isValid = true

        val nameError = if (state.name.isBlank()) {
            isValid = false
            AuthConstants.ERROR_NAME_REQUIRED
        } else null

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

        val confirmPasswordError = when {
            state.confirmPassword.isBlank() -> {
                isValid = false
                AuthConstants.ERROR_CONFIRM_PASSWORD_REQUIRED
            }
            state.confirmPassword != state.password -> {
                isValid = false
                AuthConstants.ERROR_PASSWORDS_NOT_MATCH
            }
            else -> null
        }

        _uiState.update {
            it.copy(
                nameError = nameError,
                emailError = emailError,
                passwordError = passwordError,
                confirmPasswordError = confirmPasswordError
            )
        }
        return isValid
    }
}
