package com.example.myspotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val name=findViewById<TextInputEditText>(R.id.textInputUsername)
        val pass=findViewById<TextInputEditText>(R.id.textInputPassword)
        val signUp=findViewById<Button>(R.id.btnSignUp)
        val signIn=findViewById<TextView>(R.id.tVSignIn)

        signUp.setOnClickListener {
            val naam=name.text.toString()
            val pswrd=pass.text.toString()

            val data=User(naam,pswrd)
            database=FirebaseDatabase.getInstance().getReference("User")
            database.child(naam).setValue(data).addOnSuccessListener {
                name.text?.clear()
                pass.text?.clear()
                Toast.makeText(this,"User Registered Successfully",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Some Problem Occured while Signing Up",Toast.LENGTH_SHORT).show()
            }
        }

        signIn.setOnClickListener {
            intent= Intent(this,SignIn::class.java)
            startActivity(intent)
        }
    }
}