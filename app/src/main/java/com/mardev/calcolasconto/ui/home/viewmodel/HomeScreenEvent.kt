package com.mardev.calcolasconto.ui.home.viewmodel

import android.view.View
import androidx.compose.ui.platform.ClipboardManager

/**
 * Data class that represents the HomeScreenState UI events
 */
sealed class HomeScreenEvent {
    data class OnCalculateButtonClick(val isVibrationEnabled: Boolean, val view: View) :
        HomeScreenEvent()

    data class OnClearButtonClick(val isVibrationEnabled: Boolean, val view: View) :
        HomeScreenEvent()

    data class OnCopyButtonClick(
        val isVibrationEnabled: Boolean,
        val view: View,
        val clipboardManager: ClipboardManager
    ) : HomeScreenEvent()

    data class UpdatePercentageTextInput(val percentageTextInput: String) : HomeScreenEvent()
    data class UpdatePriceTextInput(val priceTextInput: String) : HomeScreenEvent()

}