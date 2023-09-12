package com.mardev.calcolasconto.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.util.Currency
import java.util.Locale

@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String,
    imeAction: ImeAction,
) {
    val currencySymbol: String = Currency.getInstance(Locale.getDefault()).symbol

    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = {
            Text(text = placeHolderText)
        },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.8f),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Decimal, imeAction = imeAction
        ),
        suffix = {
            Text(text = currencySymbol)
        },
    )
}