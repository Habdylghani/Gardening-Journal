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
    private val plantList: LiveData<List<Plant>> = gardeningDatabase.getPlantDao().getAllPlants()


    private val selectedPlant: MutableLiveData<Plant?> = MutableLiveData()

    fun upsertPlant(item: Plant) = viewModelScope.launch {
        gardeningDatabase.getPlantDao().upsertPlant(item)
    }

    fun deletePlant(item: Plant) = viewModelScope.launch {
        gardeningDatabase.getPlantDao().deletePlant(item)
    }

    fun getAllPlants() = plantList


    fun getPlantById(plantId: Int): LiveData<Plant> {
        return gardeningDatabase.getPlantDao().getPlantById(plantId)
    }


    fun setSelectedPlantById(plantId: Int) {
        viewModelScope.launch {
            val plant = gardeningDatabase.getPlantDao().getPlantById(plantId).value
            selectedPlant.postValue(plant)
        }
    }


    fun getSelectedPlant(): MutableLiveData<Plant?> {
        return selectedPlant
    }


    fun deletePlantById(plantId: Int) = viewModelScope.launch {
        gardeningDatabase.getPlantDao().delete(plantId)
    }

}