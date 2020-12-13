package com.example.gangelkotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
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
// import com.facebook.*
import kotlinx.android.synthetic.main.activity_signin.*
//import com.facebook.login.LoginResult


class Signin : AppCompatActivity() {






        // var firebaseAuth: FirebaseAuth?=null



        //Router is used to do intents between activites
        private val router by lazy { Router() }

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
           // firebaseAuth = FirebaseAuth.getInstance()

            initialize()



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