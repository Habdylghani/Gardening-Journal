package com.example.gardeningjournal.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Plant(var name: String, var desc: String, var imageResourceId: Int){

    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}
