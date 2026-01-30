package com.sample.composenav3.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.sample.composenav3.ui.MainScreen
import com.sample.composenav3.ui.auth.login.LoginScreen
import com.sample.composenav3.ui.auth.register.RegisterScreen

@Composable
fun RootNavigation() {
    val rootBackStack = rememberNavBackStack(AppNavKey.Login)

    fun navigateToMainAndClearBackStack() {
        while (rootBackStack.isNotEmpty()) {
            rootBackStack.removeLastOrNull()
        }
        rootBackStack.add(AppNavKey.Main)
    }

    NavDisplay(
        backStack = rootBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AppNavKey.Login> {
                LoginScreen(
                    onNavigateToRegister = {
                        rootBackStack.add(AppNavKey.Register)
                    },
                    onNavigateToMain = {
                        navigateToMainAndClearBackStack()
                    }
                )
            }

            entry<AppNavKey.Register> {
                RegisterScreen(
                    onNavigateBack = {
                        rootBackStack.removeLastOrNull()
                    },
                    onNavigateToMain = {
                        navigateToMainAndClearBackStack()
                    }
                )
            }

            entry<AppNavKey.Main> {
                MainScreen()
            }
        }
    )
}
