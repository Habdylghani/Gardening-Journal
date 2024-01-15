package com.example.gardeningjournal

import AddPlantDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.PrimaryKey
import com.example.gardeningjournal.data.database.entities.Plant
import com.example.gardeningjournal.databinding.FragmentPlantDetailBinding



class PlantDetailFragment : Fragment() {

    private lateinit var viewModel: PlantDetailViewModel
    private lateinit var binding: FragmentPlantDetailBinding
    private val args: PlantDetailFragmentArgs by navArgs()

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

        viewModel.getPlantById(args.plantid).observe(viewLifecycleOwner, Observer { plant ->
            updateUI(plant)
        })

        binding.btnDelete.setOnClickListener {
                viewModel.deletePlantById(args.plantid)
                Toast.makeText(context, "The plant has been deleted", Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
        }

        binding.btnUpdate.setOnClickListener {
                updatePlant(args.plantid)
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun updateUI(plant: Plant?) {
        plant?.let {
            binding.imgPDetail.setImageResource(R.drawable.flower)
            binding.tvPName.text = plant.name
            binding.tvPDescription.text = plant.type
            binding.tvFrequency.text = plant.wateringFrequency.toString()
            binding.tvPWateringDate.text = plant.plantingDate
        }
    }


    private fun updatePlant(plantId: Int) {

        binding.etPName.visibility = View.VISIBLE
        binding.etPDescription.visibility = View.VISIBLE
        binding.etFrequency.visibility = View.VISIBLE
        binding.etPWateringDate.visibility = View.VISIBLE



        binding.etPName.setText(binding.tvPName.text.toString())
        binding.etPDescription.setText(binding.tvPDescription.text.toString())
        binding.etFrequency.setText(binding.tvFrequency.text.toString())
        binding.etPWateringDate.setText(binding.tvPWateringDate.text.toString())
        binding.btnUpdateDetail.visibility = View.VISIBLE

        binding.tvPName.visibility = View.GONE
        binding.tvPDescription.visibility = View.GONE
        binding.tvFrequency.visibility = View.GONE
        binding.tvPWateringDate.visibility = View.GONE
        binding.btnDelete.visibility = View.GONE
        binding.btnUpdate.visibility = View.GONE


        binding.btnUpdateDetail.setOnClickListener {

            val updatedPlant = Plant(
                plantId,
                name = binding.etPName.text.toString(),
                type = binding.etPDescription.text.toString(),
                wateringFrequency = binding.etFrequency.text.toString().toInt(),
                plantingDate = binding.etPWateringDate.text.toString(),
                R.drawable.flower
            )

            viewModel.upsertPlant(updatedPlant)


            updatedPlant?.let {
                binding.tvPName.text = it.name
                binding.tvPDescription.text = it.type
                binding.tvFrequency.text = it.wateringFrequency.toString()
                binding.tvPWateringDate.text = "${it.plantingDate}"
            }


            binding.tvPName.visibility = View.VISIBLE
            binding.tvPDescription.visibility = View.VISIBLE
            binding.tvFrequency.visibility = View.VISIBLE
            binding.tvPWateringDate.visibility = View.VISIBLE
            binding.btnDelete.visibility = View.VISIBLE
            binding.btnUpdate.visibility = View.VISIBLE

            binding.etPName.visibility = View.GONE
            binding.etPDescription.visibility = View.GONE
            binding.etFrequency.visibility = View.GONE
            binding.etPWateringDate.visibility = View.GONE
            binding.btnUpdateDetail.visibility = View.GONE

            Toast.makeText(context, "The plant has been updated", Toast.LENGTH_LONG).show()
        }
    }
}


