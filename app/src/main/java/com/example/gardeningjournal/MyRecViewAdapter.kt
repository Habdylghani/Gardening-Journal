import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.example.gardeningjournal.R
import com.example.gardeningjournal.data.database.entities.Plant

class MyRecViewAdapter(
    var plantlist: List<Plant>,
    private val viewModel: PlantViewModel,
    private val plantItemClickListener: PlantItemClickListener? = null
) : RecyclerView.Adapter<MyRecViewAdapter.MyViewHolder>() {

    interface PlantItemClickListener {
        fun onPlantItemClick(plant: Plant)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgPlant)
        val textView: TextView = itemView.findViewById(R.id.tvPlantName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.plant, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = plantlist.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPlant = plantlist[position]

        holder.imageView.setImageResource(currentPlant.imageResourceId)
        holder.textView.text = currentPlant.name





        holder.itemView.setOnClickListener {
            plantItemClickListener?.onPlantItemClick(currentPlant)
        }
    }
}
