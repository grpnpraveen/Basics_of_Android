package com.example.myapplication3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        val fragment =Fragment_two()
        val fragmanager=supportFragmentManager
        val fragtransaction=fragmanager.beginTransaction()
        fragtransaction.add(R.id.container1,fragment)
        fragtransaction.commit()
    }
}