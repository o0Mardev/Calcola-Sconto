package com.mardev.calcolasconto.ui.settings.viewmodel


sealed class SettingsScreenEvent{
    data class OnSaveDarkModeOption(val selectedOption: String): SettingsScreenEvent()
    data class OnSaveVibrationOption(val selectedOption: Boolean): SettingsScreenEvent()
    data class OnSaveDynamicColorOption(val selectedOption: Boolean): SettingsScreenEvent()
}
