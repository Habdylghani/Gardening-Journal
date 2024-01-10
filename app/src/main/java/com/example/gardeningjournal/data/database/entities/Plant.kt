package com.example.gardeningjournal.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val type: String,
    val wateringFrequency: Int,
    val plantingDate: String,
    val imageResourceId: Int
)


