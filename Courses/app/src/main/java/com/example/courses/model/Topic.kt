package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

//Topic(R.string.tech, 118, R.drawable.tech)
data class Topic (
    @StringRes val name: Int,
    val num: Int,
    @DrawableRes val image: Int
)