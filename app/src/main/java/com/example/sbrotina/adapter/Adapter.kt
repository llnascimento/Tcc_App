package com.example.sbrotina.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sbrotina.R
import com.example.sbrotina.api.Endpoint
import com.example.sbrotina.model.Tarefa
import com.example.sbrotina.util.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Adapter(private val data: List<Tarefa>): RecyclerView.Adapter<Adapter.ViewHolder>()
{


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txt: TextView = itemView.findViewById(R.id.textexibir)
        val card: CardView = itemView.findViewById(R.id.card_adapter)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_get, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //renomear para tarefa, objeto, sei lá
        val name = data[position]
        val cocat = name.nome.toString()
        val id = name.id
        holder.txt.text = cocat
        val context = holder.itemView.context

        holder.card.setOnClickListener{

            val alertDialog = AlertDialog.Builder(context, R.style.AlertDialogTheme)
                .setTitle("Deletar atividade")
                .setMessage("Deseja mesmo excluir a atividade?")
                .setPositiveButton("Deletar" , DialogInterface.OnClickListener { dialogInterface, i ->
                    deleteById(name.id)
                    Toast.makeText(context,"Você exclui a tarefa: ${name.nome}", Toast.LENGTH_LONG).show()
                })
                .setNegativeButton("Cancelar", null)
                .create()
            alertDialog.show()
            notifyDataSetChanged()


            val positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            context?.getColor(R.color.dialogButtonColor)?.let { positiveButton.setTextColor(it) }
            val negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            context?.getColor(R.color.dialogButtonColor)?.let { negativeButton.setTextColor(it) }
        }


    }

    fun deleteById(id : Int){
        val retrofitClient = NetworkUtils.getRetrofitInstance("http://sbrotina.somee.com")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        endpoint.deletetask(id.toString()).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful == true)
                {
                    println("Tarefa excluida com sucesso: $response")
                }
                else
                {
                    println("Tarefa não foi excluida. Falha: $response")
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Tarefa não foi excluida. Falha.")
            }
        })
    }
}