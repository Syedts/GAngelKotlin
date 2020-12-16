package com.example.gangelkotlin

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RealtimeDatabaseManager {
  //  private val databaseReference = FirebaseDatabase.getInstance().reference
    val database = Firebase.database




    fun addLocationData(Loc: String) {

        val locRef = database.getReference("Location")
        locRef.setValue(Loc)
    }
}