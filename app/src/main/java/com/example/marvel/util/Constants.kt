package com.example.marvel.util

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import java.sql.Timestamp

class Constants {
companion object{

    fun getGradientVertical():Brush{
        return Brush.verticalGradient(
            colors = listOf(
                Color(0xff4D090B),
                Color(0xff0E0202),
                Color(0xff000000)
            )
        )
    }
    fun getGradientHorizontal():Brush{
        return Brush.horizontalGradient(
            colors = listOf(
                Color(0xff4D090B),
                Color(0xff0E0202),
                Color(0xff000000)
            )
        )
    }
}
}
