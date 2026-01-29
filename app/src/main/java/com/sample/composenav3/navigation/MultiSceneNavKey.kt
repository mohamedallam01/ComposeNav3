package com.sample.composenav3.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface MultiSceneNavKey : NavKey {

    @Serializable
    data object List : MultiSceneNavKey

    @Serializable
    data class Detail(val itemId: String) : MultiSceneNavKey
}
