package com.example.gardeningjournal.database

import androidx.room.Entity


@Entity
data class Plant(val name: String, val desc: String, val imageResourceId: Int)
