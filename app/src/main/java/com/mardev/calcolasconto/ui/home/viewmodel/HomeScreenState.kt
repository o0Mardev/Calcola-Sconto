package com.mardev.calcolasconto.ui.home.viewmodel

/**
 * Data class that represents the HomeScreenState UI state
 */
data class HomeScreenState(
    var displayedDiscountedPrice: String = displayedInitialValue,
    var priceTextInput: String = "",
    var percentageTextInput: String = "",
    var displayedSaving: String = displayedInitialValue,
)