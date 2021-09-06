package com.example.myapplication3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
const val FAVOURITE_DATA="is_selected_Data"
class MainActivity5 : AppCompatActivity() {
    //  City data
    val citydata= arrayOf(city("India","delhi"),city("france","paris"),
        city("Germany","poland"),city("Italy","Rome"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        loadfavourites()  //loading saved data

        //using list view custom  from activity 4
        val customlistview=findViewById<ListView>(R.id.listviewcustom)
        val cityadapter=CityAdapter(citydata)
        customlistview.adapter=cityadapter
        customlistview.setOnItemClickListener{adapterView,view,position,id->
            var city:city=cityadapter.getItem(position)
            city.is_selected=!city.is_selected
            cityadapter.notifyDataSetChanged() //important
            Toast.makeText(this,city.country+" "+city.capital, Toast.LENGTH_SHORT).show()
            savefavourites() //important
        }
    }

    private fun savefavourites() {
        val favourites=citydata.filter{it.is_selected}.map{it.country}
        val sharedpref=getPreferences(Context.MODE_PRIVATE)
        with(sharedpref.edit()){
            putStringSet(FAVOURITE_DATA,favourites.toSet())
            commit()          //imoprtant

        }
    }

    private fun loadfavourites()
    {
        val sharedpref=getPreferences(Context.MODE_PRIVATE)
        val favourites=sharedpref.getStringSet(FAVOURITE_DATA,null)
        favourites?.forEach{
            country->
            val city=citydata.find{it.country==country}
            city?.is_selected=true
        }
    }

}

