package com.example.gardeningjournal

import AddPlantDialog
import MyRecViewAdapter
import PlantViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningjournal.data.database.entities.Plant

import com.google.android.material.floatingactionbutton.FloatingActionButton


class GardeningFragment : Fragment() {
    private lateinit var plantViewModel: PlantViewModel
    private lateinit var adapter: MyRecViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gardening, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvGardening)
        val fabAdd: FloatingActionButton = view.findViewById(R.id.fab_add)
        val btnBackGar: ImageButton = view.findViewById(R.id.btnBackGar)

        plantViewModel = ViewModelProvider(this).get(PlantViewModel::class.java)
        adapter = MyRecViewAdapter(emptyList(), plantViewModel,
            object : MyRecViewAdapter.PlantItemClickListener {
                override fun onPlantItemClick(plant: Plant) {

                    val directions =
                        com.example.gardeningjournal.GardeningFragmentDirections.actionGardeningFragmentToPlantDetailFragment(plant.id)

                    findNavController().navigate(directions)
                }
            })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        plantViewModel.getAllPlants()?.observe(viewLifecycleOwner, Observer { plants ->
            plants?.let {
                adapter.plantlist = it
                adapter.notifyDataSetChanged()
            }
        })

        fabAdd.setOnClickListener {
            showAddPlantDialog()
        }

        btnBackGar.setOnClickListener {
            findNavController().navigateUp()
        }




        return view
    }

    private fun showAddPlantDialog() {
        val addPlantDialog = AddPlantDialog(requireContext(), plantViewModel)
        addPlantDialog.show()
    }
}