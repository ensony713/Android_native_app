package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private val TAG = "DessertViewModel"

class DessertViewModel :ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState : StateFlow<DessertUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = DessertUiState()
    }

    private fun determineDessertToShow() {
        for (i in dessertList.indices) {
            if (dessertList[i].startProductionAmount <= _uiState.value.desertSold) {
                _uiState.update { currentState ->
                    currentState.copy(
                        currentIndex = i,
                        currentPrice = dessertList[i].price,
                        currentDessertId = dessertList[i].imageId
                    )
                }
                break
            }
        }
    }

    fun dessertClick() {
        // Update the revenue
        _uiState.update {currentState ->
            currentState.copy(
                revenue = currentState.revenue + currentState.currentPrice,
                desertSold = currentState.desertSold.plus(1)
            )
        }

        //Log.d(TAG, "show value ${_uiState.value.revenue}, ${_uiState.value.desertSold}")

        // Show the next dessert
        determineDessertToShow()
    }
}