package com.example.gangelkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.example.gangelkotlin.firebase.AuthenticationManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_room_cont.*
import java.lang.Math.round
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RoomCont : AppCompatActivity()
{
    private var database: FirebaseDatabase? = null
    private var myRef: DatabaseReference? = null
    var mData: RealtimeDatabaseManager? = null

    private var temperature: TextView? = null
    private var humidity: TextView? = null
    private var pressure: TextView? = null
    private var timestamp: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_cont)
        getDatabase()
        findAllViews()
        reterieveData()
    }

    private fun getDatabase() {
        // TODO: Find the reference form the database.
        database = FirebaseDatabase.getInstance()
        val mAuth = FirebaseAuth.getInstance()
        val path = "users/" + mAuth.uid // read from the user account.
        myRef = database!!.getReference(path)
    }

    private fun findAllViews() {

        temperature = findViewById(R.id.RTemp)
        humidity = findViewById(R.id.RHumidity)
        pressure = findViewById(R.id.RPressure)

      // timestamp = findViewById()
    }

    private fun reterieveData() {
        // TODO: Get the data on a single node.
        myRef!!.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val ds = dataSnapshot.getValue(RealtimeDatabaseManager::class.java)

                //val humidityRounded
              //  name!!.text = "Name: " + ds!!.name
                temperature!!.text = ds!!.temperature.toString()
                humidity!!.text =  ds!!.humidity.toString().format("%.2f",humidity)
                pressure!!.text = ds!!.pressure.toString().format("%.2f", pressure)
              //  message!!.text = "Message: " + ds.message

                // Convert from timestamp to Date and time
              //  timestamp!!.text = ds!!.timestamp?.let { convertTimestamp(it) }
            }

            private fun convertTimestamp(timestamp: String): String {
                val yourSeconds = java.lang.Long.valueOf(timestamp)
                val mDate = Date(yourSeconds * 1000)
                val df: DateFormat = SimpleDateFormat("dd MMM yyyy hh:mm:ss a")
                return df.format(mDate)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                val ds = dataSnapshot.getValue(RealtimeDatabaseManager::class.java)
              //  name!!.text = "Name:      " + ds!!.name
                temperature!!.text = ds!!.temperature.toString().format()
                humidity!!.text =  ds!!.humidity.toString().format("%.2f", humidity)
                pressure!!.text = ds!!.pressure.toString().format("%.2f", pressure)

                // Convert from timestamps to Date and time
               // timestamp!!.text = ds.timestamp?.let { convertTimestamp(it) }
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })

        // TODO: Get the whole data array on a reference.
        myRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val arraylist: MutableList<RealtimeDatabaseManager> = ArrayList()

                // TODO: Now data is retrieved, needs to process data.
                if (dataSnapshot != null && dataSnapshot.value != null) {

                    // iterate all the items in the dataSnapshot
                    for (a in dataSnapshot.children) {
                        val dataStructure = RealtimeDatabaseManager()

                        dataStructure.temperature = a.getValue(RealtimeDatabaseManager::class.java)!!.temperature
                        dataStructure.humidity = a.getValue(RealtimeDatabaseManager::class.java)!!.humidity
                        dataStructure.pressure = a.getValue(RealtimeDatabaseManager::class.java)!!.pressure
                        dataStructure.timestamp = a.getValue(RealtimeDatabaseManager::class.java)!!.timestamp
                        arraylist.add(dataStructure) // now all the data is in arraylist.

                    }
                } else {

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    private val router by lazy { Router() }
    private val authenticationManager by lazy { AuthenticationManager() }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item?.itemId) {
            R.id.nav_exit -> {
                authenticationManager.signOut(this)
                router.startLoginScreen(this)
                finish()
                true
            }
            R.id.nav_notification -> {
                router.startNotificationScreen(this)
                finish()
                true
            }
            R.id.nav_settings -> {
                router.startSettingsScreen(this)
                finish()
                true
            }
            R.id.nav_home -> {
                router.startHomeScreen(this)
                finish()
                true
            }
            R.id.nav_location -> {
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


}