package com.sample.composenav3.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.sample.composenav3.navigation.BottomNavKey
import com.sample.composenav3.ui.main.multiscene.MultiSceneScreen
import com.sample.composenav3.ui.main.profile.ProfileScreen

@Composable
fun MainScreen() {

    val homeBackStack = rememberNavBackStack(BottomNavKey.Home)
    val profileBackStack = rememberNavBackStack(BottomNavKey.Profile)

    var currentKey by rememberSaveable(stateSaver = BottomNavKey.stateSaver) {
        mutableStateOf(BottomNavKey.Home)
    }
    val currentBackStack: NavBackStack<out NavKey> = when (currentKey) {
        BottomNavKey.Home -> homeBackStack
        BottomNavKey.Profile -> profileBackStack
    }

    fun <T : NavKey> resetBackStack(backStack: NavBackStack<T>) {
        while (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    val onHandleBackPressed: () -> Unit = {
        onBackPressed(
            currentBottomKey = currentKey,
            homeBackStackSize = homeBackStack.size,
            profileBackStackSize = profileBackStack.size,
            onSetHomeKey = {
                currentKey = BottomNavKey.Home
            },
            onPopHomeBackStack = {
                homeBackStack.removeLastOrNull()
            },
            onPopProfileBackStack = {
                profileBackStack.removeLastOrNull()
            },
        )
    }


    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomNavKey.items.forEach { key ->
                    NavigationBarItem(
                        selected = key == currentKey,
                        onClick = {
                            if (currentKey != key) {
                                currentKey = key
                            } else {
                                when (key) {
                                    BottomNavKey.Home -> resetBackStack(homeBackStack)
                                    BottomNavKey.Profile -> resetBackStack(profileBackStack)
                                }
                            }
                        },
                        icon = { Icon(key.icon, contentDescription = key.label) },
                        label = { Text(key.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = currentBackStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<BottomNavKey.Home> {
                    MultiSceneScreen()
                }

                entry<BottomNavKey.Profile> {
                    ProfileScreen()
                }
            },
            transitionSpec = {
                (fadeIn() + scaleIn(initialScale = 0.92f)) togetherWith
                        (fadeOut() + scaleOut(targetScale = 0.92f))
            },
            popTransitionSpec = {
                (fadeIn() + scaleIn(initialScale = 0.92f)) togetherWith
                        (fadeOut() + scaleOut(targetScale = 0.92f))
            },
            predictivePopTransitionSpec = {
                (fadeIn() + scaleIn(initialScale = 0.92f)) togetherWith
                        (fadeOut() + scaleOut(targetScale = 0.92f))
            }
        )
    }

    BackHandler(enabled = currentBackStack.size > 1 || currentKey != BottomNavKey.Home) {
        onHandleBackPressed()
    }
}

private fun onBackPressed(
    currentBottomKey: BottomNavKey,
    homeBackStackSize: Int,
    profileBackStackSize: Int,
    onSetHomeKey: () -> Unit,
    onPopHomeBackStack: () -> Unit,
    onPopProfileBackStack: () -> Unit,
) {
    when (currentBottomKey) {
        BottomNavKey.Home -> {
            if (homeBackStackSize > 1) {
                onPopHomeBackStack()
            }
        }

        BottomNavKey.Profile -> {
            if (profileBackStackSize > 1) {
                onPopProfileBackStack()
            } else {
                onSetHomeKey()
            }
        }
    }
}
