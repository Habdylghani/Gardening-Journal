package com.example.gardeningjournal

import androidx.lifecycle.ViewModel
import com.example.gardeningjournal.data.database.entities.Plant
import com.example.gardeningjournal.data.repositories.PlantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PlantViewModel(
    private val repository : PlantRepository
): ViewModel() {

    fun upsertPlant(item: Plant) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsertPlant(item)
    }

    fun deletePlant(item: Plant) = CoroutineScope(Dispatchers.Main).launch {
        repository.deletePlant(item)
    }

    fun getAllPlants() = repository.getAllPlants()
}