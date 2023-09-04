package com.mardev.calcolasconto.ui.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mardev.calcolasconto.R
import com.mardev.calcolasconto.ui.home.viewmodel.HomeScreenEvent
import com.mardev.calcolasconto.ui.home.viewmodel.HomeScreenState
import com.mardev.calcolasconto.ui.settings.viewmodel.SettingsScreenState

@Composable
fun HomeScreen(
    state: HomeScreenState,
    appState: SettingsScreenState,
    onEvent: (HomeScreenEvent) -> Unit
) {
    val isVibrationEnabled = appState.currentVibrationOption
    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val resultModifier: Modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.medium
                )
                .size(width = 305.dp, height = 60.dp)
            val savingModifier: Modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .size(width = 305.dp, height = 48.dp)

            Spacer(modifier = Modifier.height(48.dp))

            OutputText(
                modifier = resultModifier,
                state.displayedDiscountedPrice,
                fontSize = 38.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(48.dp))

            Column {
                InputTextField(
                    value = state.priceTextInput,
                    onValueChange = { newText ->
                        onEvent(
                            HomeScreenEvent.UpdatePriceTextInput(newText)
                        )
                    },
                    placeHolderText = stringResource(id = R.string.insert_price_placeholder),
                    imeAction = ImeAction.Next
                )
                Spacer(modifier = Modifier.height(10.dp))
                InputTextField(
                    value = state.percentageTextInput,
                    onValueChange = { newText ->
                        onEvent(
                            HomeScreenEvent.UpdatePercentageTextInput(newText)
                        )
                    },
                    placeHolderText = stringResource(id = R.string.insert_percentage_placeholder),
                    imeAction = ImeAction.Done
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val view = LocalView.current
                OperationButton(stringResource(R.string.calculate_button_text)) {
                    onEvent(
                        HomeScreenEvent.OnCalculateButtonClick(isVibrationEnabled, view)
                    )
                }
                OperationButton(stringResource(R.string.clear_button_text)) {
                    onEvent(
                        HomeScreenEvent.OnClearButtonClick(isVibrationEnabled, view)
                    )
                }
            }

            Spacer(modifier = Modifier.size(30.dp))
            OutputText(
                modifier = savingModifier,
                textToDisplay = stringResource(R.string.saving_text, state.displayedSaving),
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal
            )
        }

        val view = LocalView.current
        val clipboardManager: ClipboardManager = LocalClipboardManager.current
        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(R.string.copy_result_button_text)) },
            icon = { Icon(imageVector = Icons.Filled.ContentCopy, contentDescription = null) },
            onClick = {
                onEvent(
                    HomeScreenEvent.OnCopyButtonClick(isVibrationEnabled, view, clipboardManager)
                )
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 48.dp, end = 16.dp)
        )
    }

}

@Composable
private fun OperationButton(buttonText: String, onClick: () -> Unit) {
    Button(modifier = Modifier
        .size(width = 160.dp, height = 80.dp)
        .padding(8.dp),
        onClick = {
            onClick()
        }) {
        Text(text = buttonText, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun OutputText(
    modifier: Modifier, textToDisplay: String, fontSize: TextUnit, fontWeight: FontWeight
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = textToDisplay,
            maxLines = 1,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            fontSize = fontSize,
            fontWeight = fontWeight,
            modifier = Modifier.padding(start = 8.dp)
        )
    }

}