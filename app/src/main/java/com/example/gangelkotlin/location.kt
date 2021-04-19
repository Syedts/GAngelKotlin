package com.example.gangelkotlin
import android.content.Context
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.gangelkotlin.firebase.AuthenticationManager
import kotlinx.android.synthetic.main.activity_location.*

class location : AppCompatActivity() {
    private val router by lazy { Router() }
    private val authenticationManager by lazy { AuthenticationManager() }

    private val realTimeDatabaseManager by lazy { RealtimeDatabaseManager() }

    companion object {
        fun createIntent(context: Context) = Intent(context, location::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)




    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

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

    fun SaveLocation(view: View) {

        // this gets the Location from the user and saves it in the Location Variable

        val locationContent = LocationText.text.toString()

        // Now sending this data to the data base through  RealtimeDatabaseManager class

        realTimeDatabaseManager.addLocationData(locationContent)

    }


}