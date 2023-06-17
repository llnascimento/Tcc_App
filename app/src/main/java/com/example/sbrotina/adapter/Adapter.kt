package com.example.sbrotina.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sbrotina.R
import com.example.sbrotina.model.Tarefa

class Adapter(private val data: List<Tarefa>): RecyclerView.Adapter<Adapter.ViewHolder>()
{
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txt: TextView = itemView.findViewById(R.id.textexibir)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_get, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val name = data[position]
        val cocat = name.nome.toString()

        holder.txt.text = cocat

    }
}