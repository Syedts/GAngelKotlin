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

class Home : AppCompatActivity() {
    private val router by lazy { Router() }
    private val authenticationManager by lazy { AuthenticationManager() }
    companion object {
        fun createIntent(context: Context) = Intent(context, Home::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
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
            else -> {
                if (item != null) {
                    super.onOptionsItemSelected(item)
                }
                true
            }
        }

    fun homecamClk(view: View) {
        val homecam = Intent(this, Cameras::class.java)
        startActivity(homecam)
        Toast.makeText(this, "Cameras", Toast.LENGTH_SHORT).show()
    }

    fun homedetectorsClk(view: View) {
        val homedect = Intent(this, Detectors::class.java)
        startActivity(homedect)
        Toast.makeText(this, "Detectors", Toast.LENGTH_SHORT).show()
    }

    fun notificationClk(view: View) {
        val homeNot = Intent(this, Notifications::class.java)
        startActivity(homeNot)
        Toast.makeText(this, "Notifications", Toast.LENGTH_SHORT).show()
    }

    fun SettingClk(view: View) {
        val homeSett = Intent(this, settings::class.java)
        startActivity(homeSett)
        Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
    }

    fun homeroomCLK(view: View) {
        val homeroom = Intent(this, Rooms::class.java)
        startActivity(homeroom)
        Toast.makeText(this, "Rooms", Toast.LENGTH_SHORT).show()

    }


}