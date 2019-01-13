package com.example.sandrinehallemans.kotlinmessengerapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    //val loginBtn = login_button_login
    //val backBtn = back_to_registration

    val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        login_button_login.setOnClickListener {
            performLogin()
        }

        back_to_registration.setOnClickListener {
            finish()
        }
    }

    fun performLogin() {
        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener

                Log.d(TAG, "User successfully logged in: ${it.result!!.user.uid}")
            }

            .addOnFailureListener {
                Log.d(TAG, "Failed to log user: ${it.message}")
            }
    }
}