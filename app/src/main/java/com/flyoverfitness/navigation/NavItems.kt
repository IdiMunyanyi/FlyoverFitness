package com.flyoverfitness.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.flyoverfitness.ui.screens.Screens

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)

val listOfNavItems: List<NavItem> = listOf(
    NavItem(
        label = "Home",
        icon = Icons.Outlined.Home,
        route = Screens.Home.screen
    ),
    NavItem(
        label = "Champions",
        icon = Icons.Outlined.Star,
        route = Screens.Champions.screen
    ),
    NavItem(
        label = "Sessions",
        icon = Icons.Outlined.Info,
        route = Screens.Sessions.screen
    ),
    NavItem(
        label = "Profile",
        icon = Icons.Outlined.Person,
        route = Screens.Profile.screen
    )
)