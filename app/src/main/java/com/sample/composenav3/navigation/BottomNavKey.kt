package com.sample.composenav3.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.sample.composenav3.ui.main.MainConstants
import kotlinx.serialization.Serializable

sealed interface BottomNavKey : NavKey {
    val icon: ImageVector
    val label: String

    @Serializable
    data object Profile : BottomNavKey {
        override val icon: ImageVector = Icons.Default.Person
        override val label: String = MainConstants.LABEL_PROFILE
    }

    @Serializable
    data object Home : BottomNavKey {
        override val icon: ImageVector = Icons.Default.Home
        override val label: String = MainConstants.LABEL_HOME
    }

    companion object {
        val items = listOf(Home, Profile)

        val stateSaver = Saver<BottomNavKey, String>(
            save = { it::class.qualifiedName },
            restore = { qualifiedClass ->
                items.firstOrNull { it::class.qualifiedName == qualifiedClass } ?: BottomNavKey.Home
            }
        )
    }
}