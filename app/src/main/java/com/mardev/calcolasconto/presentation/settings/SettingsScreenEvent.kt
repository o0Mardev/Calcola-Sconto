package com.mardev.calcolasconto.presentation.settings


sealed class SettingsScreenEvent{
    data class OnSaveDarkModeOption(val selectedOption: String): SettingsScreenEvent()
    data class OnSaveVibrationOption(val selectedOption: Boolean): SettingsScreenEvent()
    data class OnSaveDynamicColorOption(val selectedOption: Boolean): SettingsScreenEvent()
}
