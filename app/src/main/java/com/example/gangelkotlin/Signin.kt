package com.example.gangelkotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


import com.example.gangelkotlin.R
import com.example.gangelkotlin.Router
import com.example.gangelkotlin.firebase.AuthenticationManager
import com.example.gangelkotlin.firebase.RC_SIGN_IN
import com.example.gangelkotlin.utils.showToast
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signin.*


class Signin : AppCompatActivity() {


        // var firebaseAuth: FirebaseAuth?=null

        //Router is used to do intents between activites
        private val router by lazy { Router() }
        private lateinit var auth: FirebaseAuth
        //ref to auth manager class
        private val authenticationManager by lazy { AuthenticationManager() }

        //static function to be used in router
        companion object {
            fun createIntent(context: Context) = Intent(context, Signin::class.java)
        }

        //when activity is created
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_signin)


            auth = FirebaseAuth.getInstance()

            CreateAcc.setOnClickListener {
                router.startSignUpScreen(this)
                finish()
            }


            SignInbutton.setOnClickListener {
                doLogin()
            }
           // initialize()
        }

    private fun doLogin() {
        if (Email_Text.text.toString().isEmpty()) {
            Email_Text.error = "Please enter email"
            Email_Text.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email_Text.text.toString()).matches()) {
            Email_Text.error = "Please enter valid email"
            Email_Text.requestFocus()
            return
        }

        if (Password_Text.text.toString().isEmpty()) {
            Password_Text.error = "Please enter password"
            Password_Text.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(Email_Text.text.toString(), Password_Text.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                }
            }
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) {
            if(currentUser.isEmailVerified) {
                router.startHomeScreen(this)
                finish()
            }else{
                Toast.makeText(
                    baseContext, "Please verify your email address.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    //once we sign, check to see if results are okay or have failed
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)


            if(requestCode == RC_SIGN_IN) {

                if(resultCode == Activity.RESULT_OK) {
                    router.startHomeScreen(this)
                } else {
                    showToast("Signed in failed")
                }
            }
        }

        private fun initialize() {

            continueToHomeScreenIfUserSignedIn()

        }


        private fun continueToHomeScreenIfUserSignedIn() = if(isUserSignedIn()) router.startHomeScreen(this) else Unit


    fun googleSignInClk(view: View) {
        authenticationManager.startSignInFlow(this)
    }
        private fun isUserSignedIn() = authenticationManager.isUserSignedIn()



}