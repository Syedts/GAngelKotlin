package com.example.gangelkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun signinClk(view: View) {
        val signinBtn = Intent(this, Signin::class.java)
        startActivity(signinBtn)
        Toast.makeText(this, "Sign-in page", Toast.LENGTH_SHORT).show()
    }

    fun CreateAccClk(view: View) {
        val createAcc = Intent(this, SignUp::class.java) //// Sign in till Sign up
        startActivity(createAcc)
        Toast.makeText(this, "Sign-up page", Toast.LENGTH_SHORT).show()
    }
}