package com.example.streak.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.streak.ui.screen.streak.RepeatUnit

@Entity
data class Streak(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "count") val count: Int = 0,
    @ColumnInfo(name = "repeat_unit") val repeatUnit: String = RepeatUnit.DAY.name,
    @ColumnInfo(name = "next_period") val nextPeriod: String
)