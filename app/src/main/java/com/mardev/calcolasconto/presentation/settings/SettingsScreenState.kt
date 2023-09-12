package com.mardev.calcolasconto.presentation.settings

/**
 * Data class that represents the SettingsScreen UI state
 */
// This is used also in MainActivity for global settings
data class SettingsScreenState(
    var currentDarkModeOption: String = "follow-system",
    var currentVibrationOption: Boolean = true,
    var currentDynamicColorOption: Boolean = true
)