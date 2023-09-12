package com.mardev.calcolasconto.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.mardev.calcolasconto.presentation.main.update_dialog.components.UpdateDialog
import com.mardev.calcolasconto.presentation.settings.SettingsScreenViewModel
import com.mardev.calcolasconto.presentation.ui.theme.CalcolaScontoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {

            val mainViewModel: MainViewModel = hiltViewModel()
            val mainState by mainViewModel.state

            // This is used for sharing the same instance of the SettingsViewModel among the Activity and the Composable
            val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
            val viewModel: SettingsScreenViewModel = hiltViewModel(viewModelStoreOwner)

            val appState by viewModel.state

            CalcolaScontoTheme(
                darkTheme = when (appState.currentDarkModeOption) {
                    "dark-mode" -> true
                    "light-mode" -> false
                    else -> isSystemInDarkTheme()
                },
                dynamicColor = appState.currentDynamicColorOption
            ) {
                UpdateDialog()
                CalcolaScontoApp(appState, viewModelStoreOwner)
            }
        }
    }
}

