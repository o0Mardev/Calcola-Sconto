package com.mardev.calcolasconto.ui.settings.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mardev.calcolasconto.data.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    var currentDynamicColorOption: Boolean by mutableStateOf(getDynamicColorOption())
        private set

    var currentDarkModeOption: String by mutableStateOf(getDarkModeOption())
        private set

    var currentVibrationOption: Boolean by mutableStateOf(getVibrationOption())
        private set



    fun saveDarkModeOption(value: String) {
        currentDarkModeOption = value
        viewModelScope.launch {
            repository.putString("darkModeOption", value)
        }
    }
    fun saveVibrationOption(value: Boolean) {
        currentVibrationOption = value
        viewModelScope.launch {
            repository.putBoolean("vibrationOption", value)
        }
    }

    fun saveDynamicColorOption(value: Boolean) {
        currentDynamicColorOption = value
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
        Log.d("TAG", "viewModel: ${this.hashCode()} init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TAG", "viewmodel ${this.hashCode()} cleared")
    }
}