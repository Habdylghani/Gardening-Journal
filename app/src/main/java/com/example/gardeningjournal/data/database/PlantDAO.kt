package com.example.gardeningjournal.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.gardeningjournal.data.database.entities.Plant

@Dao
interface PlantDAO {
    @Upsert
    suspend fun upsertPlant(item: Plant)

    @Delete
    suspend fun deletePlant(item: Plant)

    @Query("DELETE FROM Plant WHERE id = :plantId")
    suspend fun delete(plantId: Int)

    @Query("SELECT * FROM Plant WHERE id = :plantId")
    fun getPlantById(plantId: Int): LiveData<Plant>


    @Query("SELECT * FROM Plant")
    fun getAllPlants(): LiveData<List<Plant>>

}