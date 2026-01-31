package com.example.navigation

import com.example.navigation.route.Route
import kotlinx.coroutines.flow.Flow

interface Navigator {
    val backStack: Flow<List<Route>>
    fun navigateTo(route: Route)
    fun goBack()
    fun currentRoute(): Route?
    fun currentRouteFlow(): Flow<Route>
}