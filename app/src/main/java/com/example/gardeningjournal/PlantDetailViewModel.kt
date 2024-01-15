package com.example.gardeningjournal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gardeningjournal.data.database.GardeningDatabase
import com.example.gardeningjournal.data.database.entities.Plant
import kotlinx.coroutines.launch

class PlantDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val gardeningDatabase = GardeningDatabase.invoke(application)

    fun upsertPlant(item: Plant) = viewModelScope.launch {
        gardeningDatabase.getPlantDao().upsertPlant(item)
    }

    fun getPlantById(plantId: Int): LiveData<Plant> {
        return gardeningDatabase.getPlantDao().getPlantById(plantId)
    }

    fun deletePlantById(plantId: Int) = viewModelScope.launch {
        gardeningDatabase.getPlantDao().delete(plantId)
    }

}