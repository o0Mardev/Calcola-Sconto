package com.mardev.calcolasconto.presentation.settings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mardev.calcolasconto.domain.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    // SettingsScreen UI state
    private val _state = mutableStateOf(SettingsScreenState())
    val state: State<SettingsScreenState> = _state


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
        _state.value = _state.value.copy(currentDarkModeOption = value)
        viewModelScope.launch {
            repository.putString("darkModeOption", value)
        }
    }

    private fun saveVibrationOption(value: Boolean) {
        _state.value = _state.value.copy(currentVibrationOption = value)
        viewModelScope.launch {
            repository.putBoolean("vibrationOption", value)
        }
    }

    private fun saveDynamicColorOption(value: Boolean) {
        _state.value = _state.value.copy(currentDynamicColorOption = value)
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
        _state.value = _state.value.copy(
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