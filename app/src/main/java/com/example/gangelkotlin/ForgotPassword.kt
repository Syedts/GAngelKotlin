package com.example.gangelkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
    }
    fun SubmitClk(view: View) {
        val FPS = Intent(this, Signin::class.java)
        startActivity(FPS)
        Toast.makeText(this, "Password sent to email", Toast.LENGTH_SHORT).show()
    }
}