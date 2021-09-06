package com.example.myapplication3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val name:String?=intent.getStringExtra("NAME")
        name?.let{
            val nameview:TextView=findViewById(R.id.name2)
//            nameview.text="Hello ${it}"
            nameview.text=getString(R.string.welcome,it)
            Toast.makeText(this,"This Activity",Toast.LENGTH_SHORT).show()

        }

    }
}