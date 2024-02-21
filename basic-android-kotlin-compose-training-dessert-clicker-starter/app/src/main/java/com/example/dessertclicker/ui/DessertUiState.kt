package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList

data class DessertUiState(
    val currentIndex: Int = 0,
    val currentPrice: Int = dessertList[currentIndex].price,
    @DrawableRes val currentDessertId: Int = dessertList[currentIndex].imageId,
    val desertSold: Int = 0,
    val revenue: Int = 0
)