import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gardeningjournal.data.database.GardeningDatabase
import com.example.gardeningjournal.data.database.entities.Plant
import kotlinx.coroutines.launch

class PlantViewModel(application: Application) : AndroidViewModel(application) {

    private val gardeningDatabase = GardeningDatabase.invoke(application)
    private val plantList: LiveData<List<Plant>> = gardeningDatabase.getPlantDao().getAllPlants()

    // New LiveData for selected plant
    private val _selectedPlant = MutableLiveData<Plant>()
    val selectedPlant: LiveData<Plant>
        get() = _selectedPlant

    fun upsertPlant(item: Plant) = viewModelScope.launch {
        gardeningDatabase.getPlantDao().upsertPlant(item)
    }

    fun deletePlant(item: Plant) = viewModelScope.launch {
        gardeningDatabase.getPlantDao().deletePlant(item)
    }

    fun getAllPlants() = plantList

    // Function to set the selected plant
    fun setSelectedPlant(plant: Plant) {
        _selectedPlant.value = plant
    }
}
