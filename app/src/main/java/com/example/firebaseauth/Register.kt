package com.example.firebaseauth

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebaseauth.databinding.ActivityRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth



class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        val exitTextEmail = binding.email
        val editTextPassword = binding.password
        val  buttonReg = binding.btnRegister

        buttonReg.setOnClickListener{
                val email = exitTextEmail.text.toString()
                val password = editTextPassword.text.toString()

            if(email.isEmpty()){
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
            }
             if(password.isEmpty()){
                 Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()

             }
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this, "account created",Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser

                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }

        }
        binding.loginNow.setOnClickListener{
            intent = Intent(this,Login::class.java)
            startActivity(intent)

        }

        }}
