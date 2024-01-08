package com.example.gardeningjournal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningjournal.database.Plant
import java.util.ArrayList

class MyRecViewAdapter(
    var plantlist: ArrayList<Plant>,
    private val onItemClick: (Plant) -> Unit
) : RecyclerView.Adapter<MyRecViewAdapter.MyViewHolder>()  {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgPlant)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecViewAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.plant, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = plantlist.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageView.setImageResource(plantlist[position].imageResourceId)
    }

}