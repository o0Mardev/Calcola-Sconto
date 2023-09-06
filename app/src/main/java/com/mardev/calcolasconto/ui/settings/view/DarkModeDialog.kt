package com.mardev.calcolasconto.ui.settings.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyAlertDialog(currentDarkModeOption: String, saveDarkModeOption: (String) -> Unit) {

    var showDialog by rememberSaveable { mutableStateOf(false) }
    var selectedRadioOption: String = currentDarkModeOption


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clickable { showDialog = !showDialog },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(imageVector = Icons.Default.DarkMode, contentDescription = null)
        Column(Modifier.padding(start = 16.dp)) {
            Text(text = "Modalità tema", fontSize = 18.sp)
            Text(
                text = when (currentDarkModeOption) {
                    "dark-mode" -> "Scura"
                    "light-mode" -> "Chiara"
                    else -> "Segui sistema"
                }
            )
        }

    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false

            },
            title = { Text("Modalità tema") },
            text = {
                Column {
                    Text("Scegli il tema dell'app")
                    RadioButtons(currentDarkModeOption) { selectedOption ->
                        Log.d("TAG", "MyAlertDialog: $selectedOption")
                        selectedRadioOption = selectedOption
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    saveDarkModeOption(
                        when (selectedRadioOption) {
                            "Chiara" -> "light-mode"
                            "Scura" -> "dark-mode"
                            else -> "follow-system"
                        }
                    )
                }) {
                    Text("Applica")
                }
            },
        )
    }
}