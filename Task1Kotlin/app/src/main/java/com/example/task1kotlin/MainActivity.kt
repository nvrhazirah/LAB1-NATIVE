package com.example.task1kotlin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.task1kotlin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.textView)
        textView.setTextColor(Color.RED)
        val button = findViewById<Button>(R.id.button)
        val buttonNavigate = findViewById<Button>(R.id.buttonNavigate)

        button.setOnClickListener {
            textView.text = "Button Clicked!"
            //Show a Toast message
            Toast.makeText(
                this@MainActivity,
                "Button was clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
        buttonNavigate.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                SecondActivity::class.java
            )
            intent.putExtra("EXTRA_MESSAGE", "Hello from MainActivity!")
            startActivity(intent)
        }
    }
}