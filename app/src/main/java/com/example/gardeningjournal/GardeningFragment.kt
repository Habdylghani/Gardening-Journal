package com.example.gardeningjournal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gardeningjournal.data.database.entities.Plant


class GardeningFragment : Fragment() {
    private val plantlist = ArrayList<Plant>()
    private lateinit var adapter: MyRecViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gardening, container, false)
    }
}