package com.example.myspotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val name_user=findViewById<TextInputEditText>(R.id.textInputUser)
        val btnIn=findViewById<Button>(R.id.btnSignIn)

        btnIn.setOnClickListener {
            val namee=name_user.text.toString()
            if(namee.isNotEmpty()) {
                readData(namee)
            }
            else
                Toast.makeText(this,"Error occured",Toast.LENGTH_SHORT).show()
        }

    }

    private fun readData(namee: String) {
        databaseReference=FirebaseDatabase.getInstance().getReference("User")
        databaseReference.child(namee).get().addOnSuccessListener{
            if(it.exists())
            {
                val intent=Intent(this,Songs::class.java)
                intent.putExtra("name",namee)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this,"Please Sign Up",Toast.LENGTH_SHORT).show()
            }
        }
    }
}