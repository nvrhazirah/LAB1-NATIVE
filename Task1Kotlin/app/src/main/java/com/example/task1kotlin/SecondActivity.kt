package com.example.task1kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.task1kotlin.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_second)


        val message = intent.getStringExtra("EXTRA_MESSAGE")
        val textView = findViewById<TextView>(R.id.textViewSecond)
        textView.text = message

        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener { v: View? -> finish() }


        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    });*/
    }
}