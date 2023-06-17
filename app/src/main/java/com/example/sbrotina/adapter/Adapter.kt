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
        val textView: TextView = itemView.findViewById(R.id.textView)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_get, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarefa = data[position]
        holder.textView.text = tarefa.nome
        holder.textView.text = tarefa.descricao

    }
}