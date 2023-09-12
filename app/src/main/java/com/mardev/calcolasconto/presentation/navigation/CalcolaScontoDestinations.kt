package com.mardev.calcolasconto.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.mardev.calcolasconto.R


interface CalcolaScontoDestinations {
    val selectedIcon:ImageVector
    val unselectedIcon:ImageVector
    val route: String
    val stringResourceId: Int
}
/**
 * Calcola sconto app navigation destinations
 */

object Home : CalcolaScontoDestinations {
    override val selectedIcon = Icons.Filled.Home
    override val unselectedIcon = Icons.Outlined.Home
    override val route = "home"
    override val stringResourceId = R.string.home

}

object Info : CalcolaScontoDestinations {
    override val selectedIcon = Icons.Filled.Info
    override val unselectedIcon = Icons.Outlined.Info
    override val route = "info"
    override val stringResourceId = R.string.info
}

object Settings : CalcolaScontoDestinations {
    override val selectedIcon = Icons.Filled.Settings
    override val unselectedIcon = Icons.Outlined.Settings
    override val route = "settings"
    override val stringResourceId = R.string.settings
}

// Screens to be displayed in the top app bar
val calcolaScontoScreens = listOf(Home, Info, Settings)