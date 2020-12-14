package com.example.gangelkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.content.Context
import android.content.SharedPreferences


import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem

import android.widget.Button
import android.widget.EditText

import com.example.gangelkotlin.PreferenceHelper.input
import com.example.gangelkotlin.PreferenceHelper.customPreference
import com.example.gangelkotlin.firebase.AuthenticationManager

class Rooms : AppCompatActivity(),View.OnClickListener {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.nav_menu, menu)

        return true
    }
    private val router by lazy { Router() }
    private val authenticationManager by lazy { AuthenticationManager() }
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
            else -> {
                if (item != null) {
                    super.onOptionsItemSelected(item)
                }
                true
            }
        }

    val CUSTOM_PREF_NAME = "User_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)

        val saveBtn = findViewById<Button>(R.id.Save)
        val showBtn = findViewById<Button>(R.id.Show)

        saveBtn.setOnClickListener(this)
        showBtn.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        val prefs = customPreference(this, CUSTOM_PREF_NAME)
        val input = findViewById<EditText>(R.id.input)
        when(v?.id){
            R.id.Save ->{
                prefs.input = input.text.toString()
            }
            R.id.Show->{
                input.setText(prefs.input)
            }


        }
    }
    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
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
object PreferenceHelper {


    val INPUT = "INPUT"

    fun defaultPreference(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    inline fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
        val key = pair.first
        val value = pair.second
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitive types can be stored in SharedPreferences")
        }
    }
    var SharedPreferences.input
        get() = getString(INPUT, "")
        set(value) {
            editMe {//
                it.putString(INPUT, value)
            }
        }
}
