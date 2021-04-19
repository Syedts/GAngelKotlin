package com.example.gangelkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

//import kotlinx.android.synthetic.main.activity_sign_up.*
//import kotlinx.android.synthetic.main.activity_signin.*

class SignUp : AppCompatActivity() {
    private val router by lazy { Router() }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

      SignUpBtn.setOnClickListener {
            signUpUser()
        }
    }
    companion object {
        fun createIntent(context: Context) = Intent(context, SignUp::class.java)
    }

    private fun signUpUser() {
        if (Email_Text2.text.toString().isEmpty()) {
            Email_Text2.error = "Please enter email"
            Email_Text2.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email_Text2.text.toString()).matches()) {
            Email_Text2.error = "Please enter valid email"
            Email_Text2.requestFocus()
            return
        }

        if (Password_Text2.text.toString().isEmpty()) {
            Password_Text2.error = "Please enter password"
            Password_Text2.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(Email_Text2.text.toString(), Password_Text2.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, Signin::class.java))
                                finish()
                            }
                        }
                } else {
                    Toast.makeText(
                        baseContext, "Their is already an account associated with that email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }



}