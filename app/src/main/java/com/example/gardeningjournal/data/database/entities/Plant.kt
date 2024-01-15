package com.example.gardeningjournal.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var type: String,
    var wateringFrequency: Int,
    var plantingDate: String,
    val imageResourceId: Int
)


