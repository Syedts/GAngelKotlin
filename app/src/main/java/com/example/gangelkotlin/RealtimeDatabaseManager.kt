package com.example.gangelkotlin

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RealtimeDatabaseManager {
  //  private val databaseReference = FirebaseDatabase.getInstance().reference

    val database = Firebase.database
    val mAuth = FirebaseAuth.getInstance()

    var temperature : Double? = null
    var humidity: Double? = null
    var pressure: Double? = null
    var timestamp: Double? = null
    var motionDet: Boolean? = null

    fun addLocationData(Loc: String) {

        val locRef = database.getReference("Locations/"+ mAuth.uid + "/Location1/")
        locRef.setValue(Loc)
    }
}