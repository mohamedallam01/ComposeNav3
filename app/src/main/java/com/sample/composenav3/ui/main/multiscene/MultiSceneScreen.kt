package com.sample.composenav3.ui.main.multiscene

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.window.core.layout.WindowSizeClass
import com.sample.composenav3.navigation.MultiSceneNavKey

@Composable
fun MultiSceneScreen() {
    val backStack = rememberNavBackStack(MultiSceneNavKey.List)
    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    // Check if the window is wide enough for multi-pane layout (840dp is the expanded breakpoint)
    val isExpanded = windowAdaptiveInfo.windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)

    if (isExpanded) {
        // Multi-pane layout for large screens
        // Show list and detail side by side
        Row(modifier = Modifier.fillMaxSize()) {
            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                ListPane(
                    onItemClick = { itemId ->
                        backStack.add(MultiSceneNavKey.Detail(itemId))
                    }
                )
            }

            // Show detail pane if there's a detail in the back stack
            val detailKey = backStack.filterIsInstance<MultiSceneNavKey.Detail>().lastOrNull()
            if (detailKey != null) {
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight()
                ) {
                    DetailPane(
                        itemId = detailKey.itemId,
                        onNavigateBack = {
                            backStack.removeLastOrNull()
                        }
                    )
                }
            }
        }
    } else {
        // Single pane layout for phones
        NavDisplay(
            backStack = backStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<MultiSceneNavKey.List> {
                    ListPane(
                        onItemClick = { itemId ->
                            backStack.add(MultiSceneNavKey.Detail(itemId))
                        }
                    )
                }

                entry<MultiSceneNavKey.Detail> { key ->
                    DetailPane(
                        itemId = key.itemId,
                        onNavigateBack = {
                            backStack.removeLastOrNull()
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun ListPane(onItemClick: (String) -> Unit) {
    // List UI will go here - just navigation logic for now
    // Call onItemClick(itemId) when an item is clicked
}

@Composable
fun DetailPane(itemId: String, onNavigateBack: () -> Unit) {
    // Detail UI will go here - just navigation logic for now
    // itemId is the selected item
    // Call onNavigateBack() to go back to list
}
