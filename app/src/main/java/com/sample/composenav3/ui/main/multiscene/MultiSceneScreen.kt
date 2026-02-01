package com.sample.composenav3.ui.main.multiscene

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.sample.composenav3.R
import com.sample.composenav3.navigation.AppNavKey
import com.sample.composenav3.navigation.BottomNavKey
import com.sample.composenav3.ui.main.MainConstants
import com.sample.composenav3.ui.main.details.DetailsScreen
import com.sample.composenav3.ui.main.home.HomeScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MultiSceneScreen() {
    val backStack = remember { mutableStateListOf<Any>(BottomNavKey.Home) }
    val strategy = rememberListDetailSceneStrategy<Any>()

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        sceneStrategy = strategy,
        entryProvider = entryProvider {
            entry<BottomNavKey.Home>(
                metadata = ListDetailSceneStrategy.listPane(
                    detailPlaceholder = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier.size(120.dp),
                                painter = painterResource(R.drawable.ic_empty_box),
                                contentDescription = MainConstants.EMPTY_BOX
                            )
                        }
                    }
                )
            ) {
                HomeScreen(
                    onNavigateToDetails = { itemId ->
                        val destination = AppNavKey.Details(itemId)
                        backStack.add(destination)
                    },
                )
            }

            entry<AppNavKey.Details>(
                metadata = ListDetailSceneStrategy.detailPane()
            ) { key ->
                DetailsScreen(itemId = key.itemId, onNavigateBack = {
                    backStack.removeLastOrNull()
                })
            }
        },
    )
}
