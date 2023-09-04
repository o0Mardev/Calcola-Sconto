package com.mardev.calcolasconto.ui.settings.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mardev.calcolasconto.data.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    // SettingsScreen UI state
    private val _uiState = MutableStateFlow(SettingsScreenState())
    val uiState: StateFlow<SettingsScreenState> = _uiState.asStateFlow()


    fun onEvent(event: SettingsScreenEvent) {
        when (event) {
            is SettingsScreenEvent.OnSaveDarkModeOption -> {
                saveDarkModeOption(event.selectedOption)
            }
            is SettingsScreenEvent.OnSaveVibrationOption -> {
                saveVibrationOption(event.selectedOption)
            }
            is SettingsScreenEvent.OnSaveDynamicColorOption -> {
                saveDynamicColorOption(event.selectedOption)
            }
        }
    }


    private fun saveDarkModeOption(value: String) {
        _uiState.value = _uiState.value.copy(currentDarkModeOption = value)
        viewModelScope.launch {
            repository.putString("darkModeOption", value)
        }
    }

    private fun saveVibrationOption(value: Boolean) {
        _uiState.value = _uiState.value.copy(currentVibrationOption = value)
        viewModelScope.launch {
            repository.putBoolean("vibrationOption", value)
        }
    }

    private fun saveDynamicColorOption(value: Boolean) {
        _uiState.value = _uiState.value.copy(currentDynamicColorOption = value)
        viewModelScope.launch {
            repository.putBoolean("dynamicColorOption", value)
        }
    }


    private fun getDarkModeOption(): String = runBlocking {
        repository.getString("darkModeOption") ?: "follow-system"
    }

    private fun getVibrationOption(): Boolean = runBlocking {
        repository.getBoolean("vibrationOption") ?: true
    }

    private fun getDynamicColorOption(): Boolean = runBlocking {
        repository.getBoolean("dynamicColorOption") ?: true
    }

    init {
        /*
        When the viewModel is initialised we set the SettingsScreenState to the values
        saved in the dataStore preferences.
         */
        Log.d("TAG", "viewModel: ${this.hashCode()} init")
        _uiState.value = _uiState.value.copy(
            currentDarkModeOption = getDarkModeOption(),
            currentVibrationOption = getVibrationOption(),
            currentDynamicColorOption = getDynamicColorOption()
        )
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TAG", "viewmodel ${this.hashCode()} cleared")
    }
}