package com.example.myspotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val n=intent.getStringExtra("Username")

        val tV6=findViewById<TextView>(R.id.textView6)

        tV6.text=n
    }
}