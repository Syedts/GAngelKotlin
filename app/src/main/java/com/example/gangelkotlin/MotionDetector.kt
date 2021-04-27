package com.example.gangelkotlin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.gangelkotlin.R.*
import com.example.gangelkotlin.firebase.AuthenticationManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MotionDetector : AppCompatActivity() {
    private var database: FirebaseDatabase? = null
    private var motionStatus: TextView? = null

    private var myRef: DatabaseReference? = null

    private val router by lazy { Router() }
    private val authenticationManager by lazy { AuthenticationManager() }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {

        menuInflater.inflate(R.menu.nav_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item?.itemId) {
            id.nav_exit -> {
                authenticationManager.signOut(this)
                router.startLoginScreen(this)
                finish()
                true
            }
            id.nav_notification -> {
                router.startNotificationScreen(this)
                finish()
                true
            }
            id.nav_settings -> {
                router.startSettingsScreen(this)
                finish()
                true
            }
            id.nav_home -> {
                router.startHomeScreen(this)
                finish()
                true
            }
            id.nav_location -> {
                router.startLocationScreen(this)
                finish()
                true
            }
            else -> {
                if (item != null) {
                    super.onOptionsItemSelected(item)
                }
                true
            }
        }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_motion_detector)
        Toast.makeText(this, "Welcome to Detectors", Toast.LENGTH_SHORT).show()

        getDatabase()
        findAllViews()
        retrieveData()


    }


    private fun getDatabase() {
        database = FirebaseDatabase.getInstance()
        val mAuth = FirebaseAuth.getInstance()
        val path = "users/" + mAuth.uid // read from the user account.
         myRef = database!!.getReference(path)
    }




    private fun findAllViews()
    {
       motionStatus = findViewById(R.id.MotionDetectedAlert)

    }


    private fun retrieveData() {

        myRef!!.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val ds = dataSnapshot.getValue(RealtimeDatabaseManager::class.java)
                val motionStatus2 = ds!!.motionDet.toString()

                if(motionStatus2 == "true")
                {
                    motionStatus!!.setText(getString(string.motResponse1))
                    motionStatus!!.setTextColor(Color.parseColor("#FF0000"))

                }
                else{
                    motionStatus!!.setText(getString(string.motResponse2))
                    motionStatus!!.setTextColor(Color.parseColor("#7FFF00"))


                }


            }

//            private fun convertTimestamp(timestamp: String): String {
//                val yourSeconds = java.lang.Long.valueOf(timestamp)
//                val mDate = Date(yourSeconds * 1000)
//                val df: DateFormat = SimpleDateFormat("dd MMM yyyy hh:mm:ss a")
//                return df.format(mDate)
//            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                val ds = dataSnapshot.getValue(RealtimeDatabaseManager::class.java)

                motionStatus!!.setText(ds!!.motionDet.toString())


            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })


        myRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val arraylist: MutableList<RealtimeDatabaseManager> = ArrayList()


                if (dataSnapshot != null && dataSnapshot.value != null) {

                    // iterate all the items in the dataSnapshot
                    for (a in dataSnapshot.children) {
                        val dataStructure = RealtimeDatabaseManager()

                        dataStructure.motionDet = a.getValue(RealtimeDatabaseManager::class.java)!!.motionDet


                        arraylist.add(dataStructure) // now all the data is in arraylist.

                    }
                } else {

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }




    fun NavHome3(view: View)
    {
        val MainHome = Intent(this, Home::class.java)
        startActivity(MainHome)
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
    }
}