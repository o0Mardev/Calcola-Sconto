package com.mardev.calcolasconto.ui.settings.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mardev.calcolasconto.ui.settings.viewmodel.SettingsViewModel


@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(text = "Aspetto")

        MyAlertDialog(viewModel.currentDarkModeOption) { selectedOption ->
            viewModel.saveDarkModeOption(selectedOption)
        }

        var dynamicColorSwitchInfo: SwitchInfo by remember {
            mutableStateOf(
                SwitchInfo(
                    viewModel.currentDynamicColorOption,
                    "Colori dinamici",
                    "Scegli se attivare i colori dinamici\n(Android 12+)",
                    Icons.Default.ColorLens
                )
            )
        }
        SwitchLine(dynamicColorSwitchInfo) { isChecked ->
            viewModel.saveDynamicColorOption(isChecked)
            dynamicColorSwitchInfo = dynamicColorSwitchInfo.copy(isChecked = isChecked)
        }

        Spacer(Modifier.height(16.dp))
        Divider()
        Spacer(Modifier.height(16.dp))

        Text(text = "Preferenze")

        var vibrationSwitchInfo: SwitchInfo by remember {
            mutableStateOf(
                SwitchInfo(
                    viewModel.currentVibrationOption,
                    "Vibrazione",
                    "Scegli se attivare la vibrazione",
                    Icons.Default.Vibration
                )
            )
        }
        SwitchLine(vibrationSwitchInfo) { isChecked ->
            viewModel.saveVibrationOption(isChecked)
            vibrationSwitchInfo = vibrationSwitchInfo.copy(isChecked = isChecked)
        }
    }
}


