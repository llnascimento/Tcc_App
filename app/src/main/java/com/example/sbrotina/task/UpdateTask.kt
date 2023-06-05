package com.example.sbrotina.task

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.sbrotina.Home
import com.example.sbrotina.R

class UpdateTask : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_task)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#FFFFFF")

        val btn = findViewById<CardView>(R.id.updateback)
        btn.setOnClickListener {
            val navegar = Intent(this, Home::class.java)
            startActivity(navegar)
        }
    }
}