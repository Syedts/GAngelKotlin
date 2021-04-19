package com.example.gangelkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gangelkotlin.firebase.AuthenticationManager
import com.example.gangelkotlin.Router
import android.os.AsyncTask
import android.util.Log
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class Home : AppCompatActivity()
{
    var city: String = "Mississauga,CA"
    val API: String = "b74a62c854248521dbd00f3d2c78e86b" // Use API key

    val database = Firebase.database
    val mAuth = FirebaseAuth.getInstance()
    val locRef = database.getReference("Locations/"+ mAuth.uid + "/Location1/" )


    private val router by lazy { Router() }
    private val authenticationManager by lazy { AuthenticationManager() }

    companion object
    {
        fun createIntent(context: Context) = Intent(context, Home::class.java)
    }

    fun getWeatherLoc()
    {
        locRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val locValue = dataSnapshot.getValue().toString()
                if(!locValue.isNullOrBlank())
                {
                    city == ""
                    city = locValue
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })

        weatherTask().execute()
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        getWeatherLoc()
    }

    override fun onStart()
    {
        super.onStart()
        //getWeatherLoc()
    }

    override fun onResume()
    {
        super.onResume()
       // getWeatherLoc()
    }

    override fun onPause()
    {
        super.onPause()
       // getWeatherLoc()
    }

    override fun onRestart()
    {
        super.onRestart()
        //getWeatherLoc()
    }
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

    fun homecamClk(view: View)
    {
        val homecam = Intent(this, Cameras::class.java)
        startActivity(homecam)
        Toast.makeText(this, "Cameras", Toast.LENGTH_SHORT).show()
    }

    fun homedetectorsClk(view: View)
    {
        val homedect = Intent(this, MotionDetector::class.java)
        startActivity(homedect)
        Toast.makeText(this, "Detectors", Toast.LENGTH_SHORT).show()
    }

    fun notificationClk(view: View)
    {
        val homeNot = Intent(this, Notifications::class.java)
        startActivity(homeNot)
        Toast.makeText(this, "Notifications", Toast.LENGTH_SHORT).show()
    }

    fun SettingClk(view: View)
    {
        val homeSett = Intent(this, settings::class.java)
        startActivity(homeSett)
        Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
    }

    fun homeroomCLK(view: View)
    {
        val homeroom = Intent(this, Rooms::class.java)
        startActivity(homeroom)
        Toast.makeText(this, "Rooms", Toast.LENGTH_SHORT).show()

    }

    inner class weatherTask() : AsyncTask<String, Void, String>()
    {
        override fun onPreExecute()
        {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String?
        {
            var response: String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$API").readText(
                        Charsets.UTF_8
                )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?)
        {
            super.onPostExecute(result)
            try
            {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")

                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt: Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt * 1000))
                val Wtemp = main.getString("temp") + "°C"

                val Wpressure = main.getString("pressure")

                val Whumidity = main.getString("humidity")

                val Waddress = jsonObj.getString("name") + ", " + sys.getString("country")

                findViewById<TextView>(R.id.address).text = Waddress
                findViewById<TextView>(R.id.updated_at).text = updatedAtText

                findViewById<TextView>(R.id.temp).text = Wtemp
                findViewById<TextView>(R.id.pressure).text = Wpressure
                findViewById<TextView>(R.id.humidity).text = Whumidity

            }
            catch (e: Exception)
            {

            }

        }



    }
}