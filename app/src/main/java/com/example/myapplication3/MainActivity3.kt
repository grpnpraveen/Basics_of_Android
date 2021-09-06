package com.example.myapplication3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        //using listview inbuilt
        val list:ListView=findViewById(R.id.listview)
        val cities= arrayOf("Mumbai","delhi","hyderabad","jaipur")
        val listAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,cities)
        list.adapter=listAdapter
        list.setOnItemClickListener{adapterView,view,position,id->
            val city: TextView=view as TextView
            Toast.makeText(this,city.text,Toast.LENGTH_SHORT).show()
        }

        //going to screen of custom list (i.e activity 5)
        val showcustmlist: Button=findViewById(R.id.showcustomlist)
        showcustmlist.setOnClickListener {
            val intent= Intent(this,MainActivity5::class.java).apply{}
            startActivity(intent)
        }

        //going to screen of editable custom list (i.e activity 6)
        val showeditablecustomlist: Button=findViewById(R.id.editablecustomlist)
        showeditablecustomlist.setOnClickListener {
            val intent=Intent(this,MainActivity6::class.java).apply{}
            startActivity(intent)
        }

        //recycler view
        val recylclerbtn:Button=findViewById(R.id.showrecycle)
        recylclerbtn.setOnClickListener {
            val intent=Intent(this,MainActivity7::class.java).apply{}
            startActivity(intent)
        }

        //fragment1
        val frag1btn:Button=findViewById(R.id.frag1)
        frag1btn.setOnClickListener {
            val intent=Intent(this,MainActivity8::class.java).apply{}
            startActivity(intent)
        }
        //fragment2
        val frag2btn:Button=findViewById(R.id.frag2)
        frag2btn.setOnClickListener {
            val intent=Intent(this,MainActivity9::class.java).apply{}
            startActivity(intent)
        }
        //fragment3
        val frag3btn:Button=findViewById(R.id.frag3)
        frag3btn.setOnClickListener {
            val intent=Intent(this,MainActivity10::class.java).apply{}
            startActivity(intent)
        }
    }
}