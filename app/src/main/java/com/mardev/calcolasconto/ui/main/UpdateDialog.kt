package com.mardev.calcolasconto.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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

@Composable
fun UpdateDialog(onConfirmButtonClick: () -> Unit) {
    var showDialog by rememberSaveable { mutableStateOf(true) }
    var showProgressIndicator by rememberSaveable { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                TextButton(onClick = {
                    showProgressIndicator = true
                    onConfirmButtonClick()
                }) {
                    Text(text = "Aggiorna")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "Ignora")
                }

            },
            title = {
                Text(text = "Nuovo aggiornamento disponibile!")
            },
            text = {
                Box {
                    Text(text = "Un nuovo aggiornamento è disponibile al download. \nScaricando l'ultimo aggiornamento avrai le ultime funzionalità, migliorie e bug fix per Calcola sconto.")
                    if (showProgressIndicator) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(top = 120.dp)
                        )
                    }
                }
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