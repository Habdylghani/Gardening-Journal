package com.example.gardeningjournal.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gardeningjournal.data.database.entities.Plant

@Database(
    entities = [Plant::class],
    version = 1
)
abstract class GardeningDatabase : RoomDatabase() {
    abstract fun getPlantDao(): PlantDAO

    companion object {
        @Volatile
        private var instance: GardeningDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): GardeningDatabase = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context): GardeningDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                GardeningDatabase::class.java,
                "GardeningDatabase"
            ).build()
    }
}
