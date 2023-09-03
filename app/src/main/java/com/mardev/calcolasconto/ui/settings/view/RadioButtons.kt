package com.mardev.calcolasconto.ui.settings.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtons(currentDarkModeOption: String, saveDarkModeOption: (String) -> Unit) {
    val radioOptions = listOf("Chiara", "Scura", "Segui sistema")

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(
            radioOptions[when (currentDarkModeOption) {
                "dark-mode" -> 1
                "light-mode" -> 0
                else -> 2
            }]
        )
    }
    Column(
        modifier = Modifier.padding(top = 18.dp)
    ) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        role = Role.RadioButton,
                        onClick = {
                            onOptionSelected(text)
                            saveDarkModeOption(text)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}