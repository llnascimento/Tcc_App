package com.example.sbrotina.task

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.sbrotina.Home
import com.example.sbrotina.R
import com.example.sbrotina.Starter
import com.example.sbrotina.api.Endpoint
import com.example.sbrotina.model.RegisterTarefaModel
import com.example.sbrotina.model.Tarefa
import com.example.sbrotina.util.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdicionarTask : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_task)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#30C7AE")


        val btn = findViewById<CardView>(R.id.taskback)
        val etnome = findViewById<EditText>(R.id.etNomeTarefa)
        val etdescri = findViewById<EditText>(R.id.etDescriTarefa)
        val etdata = findViewById<EditText>(R.id.etDataTarefa)
        val etstatus = findViewById<EditText>(R.id.etStatusTarefa)


        val btnAdicionar = findViewById<Button>(R.id.BtnAdicionarTask)

        btn.setOnClickListener {
            val navegar = Intent(this,Home::class.java)
            startActivity(navegar)
        }

        btnAdicionar.setOnClickListener {

            val nome = etnome.text.toString().trim()
            val decri = etdescri.text.toString().trim()
            val data = etdata.text.toString().trim()
            val status = etstatus.text.toString().trim()


            if (nome.isEmpty())
            {
                etnome.error = "Nome required"
                etnome.requestFocus()
                return@setOnClickListener
            }

            else if (decri.isEmpty())
            {
                etdescri.error = "Descrição required"
                etdescri.requestFocus()
                return@setOnClickListener
            }

            else if (data.isEmpty())
            {
                etdata.error = "Data required"
                etdata.requestFocus()
                return@setOnClickListener
            }

            else if (status.isEmpty())
            {
                etstatus.error = "Status required"
                etstatus.requestFocus()
                return@setOnClickListener
            }

            else{
                createTask()
            }

        }



    }

    fun createTask(){
        val etnome = findViewById<EditText>(R.id.etNomeTarefa)
        val etdescri = findViewById<EditText>(R.id.etDescriTarefa)
        val etdata = findViewById<EditText>(R.id.etDataTarefa)
        val etstatus = findViewById<EditText>(R.id.etStatusTarefa)

        val sharedPreferences = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)
        val id = sharedPreferences.getInt("id", 0)

        val retrofitClient = NetworkUtils.getRetrofitInstance("http://sbrotina.somee.com")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        var payload = RegisterTarefaModel(etnome.text.toString(), etdescri.text.toString(), etdata.text.toString(), etstatus.text.toString(), id)
        val  callback = endpoint.createtask(payload);

        callback.enqueue(object : Callback<Tarefa>{
            override fun onResponse(call: Call<Tarefa>, response: Response<Tarefa>) {

                if (response.isSuccessful == true)
                {
                    Toast.makeText(baseContext,"Registrado com Sucesso!!", Toast.LENGTH_LONG).show()
                    val navegar = Intent(this@AdicionarTask, Home::class.java)
                    startActivity(navegar)
                }

                else {
                    Toast.makeText(baseContext,"Não deu certo :!!", Toast.LENGTH_LONG).show()
                    println(response)
                }

            }

            override fun onFailure(call: Call<Tarefa>, t: Throwable) {

            }

        })


    }
}