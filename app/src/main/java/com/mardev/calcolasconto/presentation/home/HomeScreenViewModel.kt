package com.mardev.calcolasconto.presentation.home

import android.view.HapticFeedbackConstants
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.util.Locale

var displayedInitialValue = formatCurrency(0.0)
    private set


class HomeScreenViewModel: ViewModel() {

    // HomeScreen UI state
    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnCalculateButtonClick -> {
                if (event.isVibrationEnabled) {
                    event.view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                }
                calculate()
            }

            is HomeScreenEvent.OnClearButtonClick -> {
                if (event.isVibrationEnabled) {
                    event.view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                }
                _state.value = HomeScreenState()
            }

            is HomeScreenEvent.OnCopyButtonClick -> {
                if (event.isVibrationEnabled) {
                    event.view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                }
                event.clipboardManager.setText(AnnotatedString(_state.value.displayedDiscountedPrice))
            }

            is HomeScreenEvent.UpdatePriceTextInput -> {
                _state.value = _state.value.copy(
                    priceTextInput = event.priceTextInput
                )
            }

            is HomeScreenEvent.UpdatePercentageTextInput -> {
                _state.value = _state.value.copy(
                    percentageTextInput = event.percentageTextInput
                )
            }
        }
    }

    private fun calculate() {
        val price = _state.value.priceTextInput.toDoubleOrNull()
        val percentage = _state.value.percentageTextInput.toDoubleOrNull()

        if (price != null && percentage != null) {
            val discountedPrice = price * (1 - percentage / 100)
            val saving = price - discountedPrice

            _state.value = _state.value.copy(
                displayedDiscountedPrice = formatCurrency(discountedPrice),
                displayedSaving = formatCurrency(saving)
            )
        }
    }
}

private fun formatCurrency(number: Double): String {
    val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return numberFormat.format(number)
}
