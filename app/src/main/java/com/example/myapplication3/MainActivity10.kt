package com.example.myapplication3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity10 : AppCompatActivity(),Fragment_three.OnMessageClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)

        val frag=Fragment_three()
        val fragmanager=supportFragmentManager
        val fragtransaction=fragmanager.beginTransaction()
        fragtransaction.add(R.id.container3,frag,"frag3")
        fragtransaction.commit()

        val btn4:Button=findViewById(R.id.button4)
        btn4.setOnClickListener{
            val frag=supportFragmentManager.findFragmentByTag("frag3") as Fragment_three
            frag.showmsg()

        }


    }

    override fun onMessageClick() {
        showactmsg()
    }
    fun showactmsg()
    {
        Toast.makeText(this,"This from activity",Toast.LENGTH_SHORT).show()
    }
}