package com.example.sandrinehallemans.kotlinmessengerapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    private lateinit var mAuth: FirebaseAuth

    //private val registerBtn: Button = register_button_register
    //private val hasAccountBtn: TextView  = already_has_account_textview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_button_register.setOnClickListener {
            performRegister()
        }

        already_has_account_textview.setOnClickListener {
            toLoginActivity()
        }
    }

    fun performRegister() {
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email and pwd fields", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth = FirebaseAuth.getInstance()

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                Log.d(TAG, "Successfully created user with uid: ${it.result!!.user.uid}")
            }

            .addOnFailureListener {
                Log.d(TAG, "Failed to create user: ${it.message}")
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    fun toLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
