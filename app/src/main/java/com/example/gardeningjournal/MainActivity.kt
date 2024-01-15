package com.example.gardeningjournal

import PlantViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gardeningjournal.data.database.GardeningDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var mNavController: NavController
    private lateinit var viewModel: PlantViewModel
    private lateinit var gardeningDatabase: GardeningDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gardeningDatabase = GardeningDatabase(this)

        viewModel = ViewModelProvider(this).get(PlantViewModel::class.java)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        mNavController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp()
    }
}
