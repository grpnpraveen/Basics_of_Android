package com.example.myapplication3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentTransaction

class MainActivity9 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        val addbtn:Button=findViewById(R.id.button)
        val removebtn:Button=findViewById(R.id.button2)
        val replacebtn:Button=findViewById(R.id.button3)

        //add
        addbtn.setOnClickListener {
            val frag=Fragment_one()
            val fragmanager=supportFragmentManager
            val fragtransaction=fragmanager.beginTransaction()
            fragtransaction.add(R.id.container2,frag,"frag1")
            fragtransaction.commit()
        }
        //remove
        removebtn.setOnClickListener {
            val frag=supportFragmentManager.findFragmentByTag("frag1")
            frag?.let{
                supportFragmentManager.beginTransaction().remove(frag).commit()
            }
        }
        //replace
        replacebtn.setOnClickListener {
            val frag=Fragment_two()
            with(supportFragmentManager.beginTransaction()){
                replace(R.id.container2,frag)
                addToBackStack(null)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                commit()

            }
        }


    }
}