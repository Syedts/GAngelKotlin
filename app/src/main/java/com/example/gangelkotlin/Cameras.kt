package com.example.gangelkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class Cameras : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cameras)
    }

    fun NavHome(view: View) {
        val MainHome = Intent(this, Home::class.java)
        startActivity(MainHome)
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()

    }
}