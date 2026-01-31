package com.example.navigation

import com.example.navigation.route.AlbumListScreenRoute
import com.example.navigation.route.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class NavigatorImpl @Inject constructor(): Navigator {
    private val _backStack  = MutableStateFlow<List<Route>>(listOf(AlbumListScreenRoute))
    override val backStack: Flow<List<Route>> = _backStack

    override fun navigateTo(route: Route) {
        _backStack.update { currentStack ->
            currentStack + route
        }
    }

    override fun goBack() {
        _backStack.update {
            if (it.size > 1) {
                it.dropLast(1)
            } else {
                it
            }
        }
    }

    override fun currentRoute(): Route? {
        return _backStack.value.lastOrNull()
    }

    override fun currentRouteFlow(): Flow<Route> {
        return backStack.mapNotNull { it.lastOrNull() }
    }
}