package com.mardev.calcolasconto.presentation.main

import android.content.Context
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mardev.calcolasconto.presentation.common.components.CalcolaScontoAppBar
import com.mardev.calcolasconto.presentation.common.components.NavigationDrawer
import com.mardev.calcolasconto.presentation.home.components.HomeScreen
import com.mardev.calcolasconto.presentation.home.HomeScreenState
import com.mardev.calcolasconto.presentation.home.HomeScreenViewModel
import com.mardev.calcolasconto.presentation.info.components.InfoScreen
import com.mardev.calcolasconto.presentation.navigation.Home
import com.mardev.calcolasconto.presentation.navigation.Info
import com.mardev.calcolasconto.presentation.navigation.Settings
import com.mardev.calcolasconto.presentation.navigation.calcolaScontoScreens
import com.mardev.calcolasconto.presentation.settings.components.SettingsScreen
import com.mardev.calcolasconto.presentation.settings.SettingsScreenState
import com.mardev.calcolasconto.presentation.settings.SettingsScreenViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcolaScontoApp(
    appState: SettingsScreenState,
    viewModelStoreOwner: ViewModelStoreOwner
) {


    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var appBarTitle by rememberSaveable { mutableStateOf("Home") }
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }


    navController.addOnDestinationChangedListener { _, currentDestination, _ ->
        appBarTitle = getTitleByCurrentNavDestination(context, currentDestination)
        selectedItemIndex = getSelectedItemIndexByCurrentNavDestination(currentDestination)
        scope.launch {
            drawerState.close()
        }
    }

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    NavigationDrawer(
        drawerState = drawerState,
        scope = scope,
        onNavigationDrawerItemClick = { itemClicked ->
            navController.navigateSingleTopTo(itemClicked.route)
        },
        selectedItemIndex = selectedItemIndex,
        content = {
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    CalcolaScontoAppBar(
                        appBarTitle = appBarTitle,
                        scrollBehavior,
                        onNavigationIconClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        })
                },
                content = { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Home.route,
                        enterTransition = {
                            fadeIn()
                        },
                        exitTransition = {
                            fadeOut()
                        },
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        composable(route = Home.route) {
                            val viewModel: HomeScreenViewModel = hiltViewModel()
                            val state: HomeScreenState by viewModel.state

                            HomeScreen(
                                state = state,
                                appState = appState,
                                onEvent = viewModel::onEvent
                            )
                        }
                        composable(route = Info.route) {
                            InfoScreen(
                                appState = appState
                            )
                        }
                        composable(route = Settings.route) {
                            val viewModel: SettingsScreenViewModel =
                                hiltViewModel(viewModelStoreOwner)

                            SettingsScreen(
                                state = appState,
                                onEvent = viewModel::onEvent
                            )
                        }
                    }
                }
            )
        }
    )
}


private fun getSelectedItemIndexByCurrentNavDestination(currentNavDestination: NavDestination): Int {
    return when (currentNavDestination.route) {
        calcolaScontoScreens[0].route -> 0
        calcolaScontoScreens[1].route -> 1
        calcolaScontoScreens[2].route -> 2

        else -> 0
    }
}

private fun getTitleByCurrentNavDestination(
    context: Context,
    currentNavDestination: NavDestination
): String {
    return when (currentNavDestination.route) {
        calcolaScontoScreens[0].route -> context.getString(calcolaScontoScreens[0].stringResourceId)
        calcolaScontoScreens[1].route -> context.getString(calcolaScontoScreens[1].stringResourceId)
        calcolaScontoScreens[2].route -> context.getString(calcolaScontoScreens[2].stringResourceId)

        else -> context.getString(calcolaScontoScreens[0].stringResourceId)
    }
}

private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }