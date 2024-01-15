package com.example.gardeningjournal

import AddPlantDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gardeningjournal.data.database.entities.Plant
import com.example.gardeningjournal.databinding.FragmentPlantDetailBinding



class PlantDetailFragment : Fragment() {

    private lateinit var viewModel: PlantDetailViewModel
    private lateinit var binding: FragmentPlantDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PlantDetailViewModel::class.java)

        viewModel.getSelectedPlant().observe(viewLifecycleOwner, Observer { plant ->
            updateUI(plant)
        })

        val plantId = arguments?.getInt("plantid") ?: 0
        if (plantId > 0) {
            viewModel.setSelectedPlantById(plantId)
        }

        binding.btnDelete.setOnClickListener {
            viewModel.getSelectedPlant().value?.let { plant ->
                viewModel.deletePlantById(plant.id)
            }
        }

        binding.btnUpdate.setOnClickListener {
            viewModel.getSelectedPlant().value?.let { plant ->
                UpdatePlant(plant)
            }
        }


        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun updateUI(plant: Plant?) {
        plant?.let {
            binding.imgPDetail.setImageResource(plant.imageResourceId)
            binding.tvPName.text = plant.name
            binding.tvPDescription.text = plant.type
            binding.tvPWateringDate.text = "Watering Date: ${plant.plantingDate}"
        }
    }

    private fun UpdatePlant(plant: Plant) {
        binding.tvPName.visibility = View.GONE
        binding.tvPDescription.visibility = View.GONE
        binding.tvPWateringDate.visibility = View.GONE

        binding.etPName.visibility = View.VISIBLE
        binding.etPDescription.visibility = View.VISIBLE
        binding.etPWateringDate.visibility = View.VISIBLE

        binding.etPName.setText(plant.name)
        binding.etPDescription.setText(plant.type)
        binding.etPWateringDate.setText(plant.plantingDate)

        binding.btnUpdate.setOnClickListener {
            binding.tvPName.text = binding.etPName.text.toString()
            binding.tvPDescription.text = binding.etPDescription.text.toString()
            binding.tvPWateringDate.text = "Watering Date: ${binding.etPWateringDate.text.toString()}"

            binding.tvPName.visibility = View.VISIBLE
            binding.tvPDescription.visibility = View.VISIBLE
            binding.tvPWateringDate.visibility = View.VISIBLE

            binding.etPName.visibility = View.GONE
            binding.etPDescription.visibility = View.GONE
            binding.etPWateringDate.visibility = View.GONE
        }

    }

}


