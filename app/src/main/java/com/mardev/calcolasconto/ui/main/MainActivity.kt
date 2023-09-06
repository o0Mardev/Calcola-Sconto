package com.mardev.calcolasconto.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.mardev.calcolasconto.UpdateManager
import com.mardev.calcolasconto.ui.settings.viewmodel.SettingsScreenViewModel
import com.mardev.calcolasconto.ui.theme.CalcolaScontoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            val updateManager = UpdateManager(applicationContext)
            var isUpdateAvailable by remember {
                mutableStateOf(false)
            }

            val coroutineScope = CoroutineScope(Dispatchers.Main + Job())

            //We pass true to call only once
            LaunchedEffect(true) {
                val result = coroutineScope.async(Dispatchers.IO) {
                    return@async updateManager.checkForAppUpdate()
                }
                result.invokeOnCompletion {
                    if (it == null) {
                        Log.d("TAG", "onCreate: ${result.getCompleted()}")
                        isUpdateAvailable = result.getCompleted()
                    }
                }
            }

            // This is used for sharing the same instance of the SettingsViewModel among the Activity and the Composable
            val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
            val viewModel: SettingsScreenViewModel = hiltViewModel(viewModelStoreOwner)

            val appState by viewModel.uiState.collectAsState()

            CalcolaScontoTheme(
                darkTheme = when (appState.currentDarkModeOption) {
                    "dark-mode" -> true
                    "light-mode" -> false
                    else -> isSystemInDarkTheme()
                },
                dynamicColor = appState.currentDynamicColorOption
            ) {
                CalcolaScontoApp(appState, viewModelStoreOwner)
                if (isUpdateAvailable) {
                    UpdateDialog {
                        Toast.makeText(this, "Sto scaricando l'aggiornamento", Toast.LENGTH_LONG).show()
                        coroutineScope.launch(Dispatchers.IO) {
                            updateManager.update()
                        }
                    }
                }
            }
        }
    }
}