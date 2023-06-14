package com.example.sbrotina.task

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.sbrotina.Home
import com.example.sbrotina.R

class DeleteTask : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_task)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#30C7AE")

        val btn = findViewById<CardView>(R.id.deletecardback)
        btn.setOnClickListener {
            val navegar = Intent(this, Home::class.java)
            startActivity(navegar)
        }
    }
}