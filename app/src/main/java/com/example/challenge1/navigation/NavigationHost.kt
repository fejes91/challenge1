package com.example.challenge1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.albumdetails.ui.AlbumDetailsScreen
import com.example.albumlist.ui.AlbumListScreen
import com.example.navigation.route.AlbumDetailsScreenRoute
import com.example.navigation.route.AlbumListScreenRoute

@Composable
fun NavigationHost(viewModel: NavigatorViewModel = hiltViewModel()) {
    val backStack by viewModel.navigator.backStack.collectAsStateWithLifecycle(emptyList())

    if(backStack.isEmpty()) {
        return
    }

    NavDisplay(
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack,
        onBack = { viewModel.navigator.goBack() },
        entryProvider = { key ->
            when (key) {
                is AlbumListScreenRoute -> NavEntry(key) {
                    AlbumListScreen()
                }

                is AlbumDetailsScreenRoute -> NavEntry(key) {
                    AlbumDetailsScreen()
                }
            }
        }
    )
}