package com.example.gangelkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class Rooms : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)
    }

    fun roomsbackClk(view: View) {
        val roomsBack = Intent(this, Home::class.java)
        startActivity(roomsBack)
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
    }

    fun NavHomeClk(view: View) {
        val MainHome = Intent(this, Home::class.java)
        startActivity(MainHome)
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
    }

    fun room1Clk(view: View) {
        val RoomHome = Intent(this, RoomCont::class.java)
        startActivity(RoomHome)
        Toast.makeText(this, "Room 1", Toast.LENGTH_SHORT).show()
    }
}