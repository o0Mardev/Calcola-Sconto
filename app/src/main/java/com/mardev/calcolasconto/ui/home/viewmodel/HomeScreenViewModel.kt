package com.mardev.calcolasconto.ui.home.viewmodel

import android.view.HapticFeedbackConstants
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.NumberFormat
import java.util.Locale

var displayedInitialValue = formatCurrency(0.0)
    private set


class HomeScreenViewModel: ViewModel() {

    // HomeScreen UI state
    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState.asStateFlow()

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnCalculateButtonClick -> {
                if (event.isVibrationEnabled){
                    event.view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                }
                calculate()
            }

            is HomeScreenEvent.OnClearButtonClick -> {
                if (event.isVibrationEnabled){
                    event.view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                }
                _uiState.value = HomeScreenState()
            }

            is HomeScreenEvent.OnCopyButtonClick -> {
                if (event.isVibrationEnabled){
                    event.view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                }
                event.clipboardManager.setText(AnnotatedString(uiState.value.displayedDiscountedPrice))
            }

            is HomeScreenEvent.UpdatePriceTextInput -> {
                _uiState.value = _uiState.value.copy(
                    priceTextInput = event.priceTextInput
                )
            }

            is HomeScreenEvent.UpdatePercentageTextInput -> {
                _uiState.value = _uiState.value.copy(
                    percentageTextInput = event.percentageTextInput
                )
            }
        }
    }

    private fun calculate() {
        val price = _uiState.value.priceTextInput.toDoubleOrNull()
        val percentage = _uiState.value.percentageTextInput.toDoubleOrNull()

        if (price != null && percentage != null) {
            val discountedPrice = price * (1 - percentage / 100)
            val saving = price - discountedPrice

            _uiState.value = _uiState.value.copy(
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
