package com.example.gardeningjournal

import com.example.gardeningjournal.data.database.entities.Plant

interface AddDialogListener {
    fun onAddButtonClicked(item: Plant)
}