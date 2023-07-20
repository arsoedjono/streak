package com.example.streak.util.converter

import com.example.streak.data.entity.Streak
import com.example.streak.ui.screen.streak.RepeatUnit

fun Streak.humanizedRepeatUnit(): String {
    return when(this.repeatUnit) {
        RepeatUnit.DAY.name -> "days"
        else -> ""
    }
}