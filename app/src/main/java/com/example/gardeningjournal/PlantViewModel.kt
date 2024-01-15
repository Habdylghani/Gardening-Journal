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

    fun upsertPlant(item: Plant) = viewModelScope.launch {
        gardeningDatabase.getPlantDao().upsertPlant(item)
    }

    fun getAllPlants() = plantList

}
