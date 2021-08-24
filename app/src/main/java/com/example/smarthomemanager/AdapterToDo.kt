package com.example.smarthomemanager

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthomemanager.db.CurrentReadings
import com.example.smarthomemanager.settings.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_todo.*

class AdapterToDo (var tasks: Array<String?> , val context: Context): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_to_do,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.todo.text = tasks[position]
        holder.ibtDelete.setOnClickListener {
           tasks[position]=""
            SharedPrefManager(context).setText(position.toString(),tasks[position].toString())
        }
    }

    override fun getItemCount(): Int {
       return tasks.size
    }
}