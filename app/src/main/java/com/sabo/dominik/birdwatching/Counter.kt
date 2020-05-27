package com.sabo.dominik.birdwatching

import android.graphics.Color

class Counter {
    var count: Int = 0
    var backgroundColour: Int = Color.CYAN

    fun increment(){
        count++
    }

    fun reset(){
        count = 0
    }
}