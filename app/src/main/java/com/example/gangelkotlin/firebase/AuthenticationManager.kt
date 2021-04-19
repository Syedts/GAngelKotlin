package com.example.gangelkotlin.firebase

import android.app.Activity
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import android.content.Context
import android.content.Intent

const val RC_SIGN_IN = 1000

class AuthenticationManager {

    //get instance of firebase auth
    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    //get list of providers
    private val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())

    fun startSignInFlow(activity: Activity) {
        activity.startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .setIsSmartLockEnabled(false).build(),
            RC_SIGN_IN
        )
    }

    fun isUserSignedIn() = firebaseAuth.currentUser != null

    fun getCurrentUser() = firebaseAuth.currentUser?.displayName ?: ""

    fun signOut(context: Context) {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build()
        val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)

        firebaseAuth.signOut()

        googleSignInClient.signOut()
    }
}