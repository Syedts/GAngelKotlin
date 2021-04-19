package com.example.gangelkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.gangelkotlin.firebase.AuthenticationManager

class MotionDetector : AppCompatActivity() {

    private val router by lazy { Router() }
    private val authenticationManager by lazy { AuthenticationManager() }
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_detector)
        Toast.makeText(this, "Welcome to Detectors", Toast.LENGTH_SHORT).show()


    }

    fun NavHome3(view: View) {
        val MainHome = Intent(this, Home::class.java)
        startActivity(MainHome)
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()

    }
}