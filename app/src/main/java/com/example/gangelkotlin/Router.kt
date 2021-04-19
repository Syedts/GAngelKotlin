package com.example.gangelkotlin


import android.app.Activity
import com.example.gangelkotlin.Signin
import com.example.gangelkotlin.Home



class Router {

    fun startHomeScreen(activity: Activity) {
        val home = Home.createIntent(activity)
        activity.startActivity(home)
    }

    fun startLoginScreen(activity: Activity) {
        val intent = Signin.createIntent(activity)
        activity.startActivity(intent)
    }


    fun startNotificationScreen(activity: Activity){
        val intent = Notifications.createIntent(activity)
        activity.startActivity(intent)
    }
    fun startSettingsScreen(activity: Activity){
        val intent = settings.createIntent(activity)
        activity.startActivity(intent)
    }

    fun startSignUpScreen ( activity: Activity){
        val intent = SignUp.createIntent(activity)
        activity.startActivity(intent)
    }
    fun startLocationScreen ( activity: Activity){
            val intent = location.createIntent(activity)
        activity.startActivity(intent)
    }
}