package com.example.gangelkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.database.core.view.View

class Cameras : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cameras)
    }

    fun NavHome(view : android.view.View) {
        val MainHome = Intent(this, Home::class.java)
        startActivity(MainHome)
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()

    }
}