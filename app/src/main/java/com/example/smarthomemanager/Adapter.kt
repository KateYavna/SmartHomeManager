package com.example.smarthomemanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthomemanager.db.CurrentReadings
import kotlinx.android.synthetic.main.item_current_readings.view.*
import kotlinx.android.synthetic.main.item_to_do.view.*

class Adapter (val currentReadings: List<CurrentReadings>, val context: Context): RecyclerView.Adapter<ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_current_readings,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.data.text = currentReadings[position].data
        holder.value.text = currentReadings[position].value
    }

    override fun getItemCount(): Int {
        return currentReadings.size
    }
}
class ViewHolder (ItemView:View):RecyclerView.ViewHolder(ItemView){
    val data = itemView.tvData
    val value = itemView.tvValue
    val todo = itemView.tvToDo
    val ibtDelete = itemView.ibtDelete
}