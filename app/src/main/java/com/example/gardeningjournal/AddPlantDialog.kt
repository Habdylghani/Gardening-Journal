import android.content.Context
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialog

import com.example.gardeningjournal.R
import com.example.gardeningjournal.data.database.entities.Plant

class AddPlantDialog(
    context: Context,
    private val plantViewModel: PlantViewModel
) : AppCompatDialog(context) {

    private lateinit var etName: EditText
    private lateinit var etDesc: EditText
    private lateinit var etDate: EditText
    private lateinit var etFrq: EditText
    private lateinit var addBtn: Button
    private lateinit var cancelBtn: Button

    init {
        initViews()
        initListeners()
    }

    private fun initViews() {
        setContentView(R.layout.dialog_add_plant)

        etName = findViewById(R.id.etName)!!
        etDesc = findViewById(R.id.etDesc)!!
        etDate = findViewById(R.id.etDate)!!
        etFrq = findViewById(R.id.etFrq)!!
        addBtn = findViewById(R.id.AddBtn)!!
        cancelBtn = findViewById(R.id.cancelBtn)!!
    }

    private fun initListeners() {
        addBtn.setOnClickListener {
            addPlant()
        }

        cancelBtn.setOnClickListener {
            dismiss()
        }
    }

    private fun addPlant() {
        val plantName = etName.text.toString()
        val plantDesc = etDesc.text.toString()
        val wateringDate = etDate.text.toString()
        val frequency = etFrq.text.toString()

        // Add your logic to handle the input values, validate them, and create a new Plant object
        val newPlant = Plant(
            name = plantName,
            type = plantDesc,
            wateringFrequency = frequency.toInt(),
            plantingDate = wateringDate,
            imageResourceId = R.drawable.flower
        )

        plantViewModel.upsertPlant(newPlant)

        dismiss()
    }
}
