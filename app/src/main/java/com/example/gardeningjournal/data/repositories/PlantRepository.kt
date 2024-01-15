package com.example.gardeningjournal.data.repositories

import com.example.gardeningjournal.data.database.GardeningDatabase
import com.example.gardeningjournal.data.database.entities.Plant

class PlantRepository (
    private val db : GardeningDatabase
){

    suspend fun upsertPlant(item: Plant) = db.getPlantDao().upsertPlant(item)

    suspend fun deletePlant(item: Plant) = db.getPlantDao().deletePlant(item)

    fun getAllPlants() = db.getPlantDao().getAllPlants()
}