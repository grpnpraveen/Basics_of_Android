package com.example.myapplication3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

val countries= mutableListOf<country>(country("aus","Australia","Canberra",R.drawable.aus),
country("bang","Bangladesh","Dhaka",R.drawable.bang),
country("can","Canada","Ottawa",R.drawable.can),
country("ger","Germany","Poland",R.drawable.ger),
country("ind","India","New Delhi",R.drawable.ind),
country("ire","Ireland","Dublin",R.drawable.ire)
,country("jap","Japan","Tokyo",R.drawable.jap),
country("uk","United Kingdom","London",R.drawable.uk),
country("us","United States","Washington DC",R.drawable.us),
country("aus","Australia","Canberra",R.drawable.aus),
country("bang","Bangladesh","Dhaka",R.drawable.bang),
country("can","Canada","Ottawa",R.drawable.can),
country("ger","Germany","Poland",R.drawable.ger),
country("ind","India","New Delhi",R.drawable.ind),
country("ire","Ireland","Dublin",R.drawable.ire)
,country("jap","Japan","Tokyo",R.drawable.jap),
country("uk","United Kingdom","London",R.drawable.uk))

class MainActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)


        val countrylistview=findViewById<RecyclerView>(R.id.recyclerview).apply{
            layoutManager=LinearLayoutManager(this@MainActivity7)
            adapter=CountryAdapter{
                Toast.makeText(this@MainActivity7,"country: ${it.name} is touched",Toast.LENGTH_SHORT).show()
            }.apply{
                setHasStableIds(true) //optimisation
            }
            setHasFixedSize(true)  //optimisation
        }

        val showbtn: Button=findViewById(R.id.showmore)
        showbtn.setOnClickListener {

                (countrylistview.adapter as CountryAdapter).countrydata= countries.shuffled()
        }

    }
}