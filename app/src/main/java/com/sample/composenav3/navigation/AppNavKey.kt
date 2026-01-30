package com.sample.composenav3.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface AppNavKey : NavKey {

    @Serializable
    data object Login : AppNavKey

    @Serializable
    data object Register : AppNavKey

    @Serializable
    data object Main : AppNavKey

    @Serializable
    data class Details(val itemId: String) : AppNavKey
}
