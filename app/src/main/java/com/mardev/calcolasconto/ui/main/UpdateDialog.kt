package com.mardev.calcolasconto.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun UpdateDialog(onConfirmButtonClick: () -> Unit) {
    var showDialog by rememberSaveable { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    onConfirmButtonClick()
                }) {
                    Text(text = "Aggiorna")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "Annulla")
                }
            },
            title = {
                Text(text = "Nuovo aggiornamento disponibile")
            },
            text = {
                Text(text = "L'aggiornamento è disponibile al download.\nScaricando l'ultimo aggiornamento si otterranno le ultime funzionalità, i miglioramenti e le correzioni di bug di Calcola Sconto.")
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.SystemUpdate,
                    contentDescription = null
                )
            }
        )
    }
}