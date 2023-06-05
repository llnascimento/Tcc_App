package com.example.sbrotina.task

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView
import com.example.sbrotina.Home
import com.example.sbrotina.HomeFragment
import com.example.sbrotina.R
import com.example.sbrotina.Register
import com.example.sbrotina.databinding.ActivityAdicionarTaskBinding

class AdicionarTask : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_task)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#FFFFFF")


        val btn = findViewById<CardView>(R.id.taskback)

        btn.setOnClickListener {
            val navegar = Intent(this,Home::class.java)
            startActivity(navegar)
        }



    }
}