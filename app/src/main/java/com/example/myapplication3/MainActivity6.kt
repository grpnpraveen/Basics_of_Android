package com.example.myapplication3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson

const val FAVOURITE_DATA2="data2"

class MainActivity6 : AppCompatActivity() {
    // City data
    var citydata= mutableListOf<city>()
    lateinit var cityadapter:CityAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        loadcities()
        loadfavourites()
        //reset
        val resetbtn: Button =findViewById(R.id.resetitem)
        resetbtn.setOnClickListener {
            resetList()

        }
        //add
        val addbtn:Button =findViewById(R.id.additem)
        addbtn.setOnClickListener {
            addcity()

        }

        val listview: ListView=findViewById(R.id.listview)
        cityadapter=CityAdapter2(citydata)
        listview.adapter=cityadapter
        listview.setOnItemClickListener{adapterView,view,position,id->
            var city:city=cityadapter.getItem(position)
            city.is_selected=!city.is_selected
            cityadapter.notifyDataSetChanged() //important
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
    private fun loadcities()
    {
        val sharedpref=getPreferences(Context.MODE_PRIVATE)
        val cities=sharedpref.getStringSet(FAVOURITE_DATA2,null)
        val gson=Gson()
        cities?.forEach{
            citydata.add(gson.fromJson(it,city::class.java))
        }
    }
    private fun savecities()
    {
        val gson=Gson()
        val cities=citydata.map{gson.toJson(it)}
        val sharedpref=getPreferences(Context.MODE_PRIVATE)
        with(sharedpref.edit())
        {
            putStringSet(FAVOURITE_DATA2,cities.toSet())
            commit()

        }
        cityadapter.notifyDataSetChanged()
    }

    private fun resetList()
    {
        val builder=AlertDialog.Builder(this)
        with(builder){
            setTitle("Confirm Reset")
            setMessage("Confirm reset List")
            setPositiveButton("yes"){dialog,which->
                citydata.clear()
                savecities()
            }
            setNegativeButton("No"){dialog,which->
                Log.d("CRASHED","HERE")
            }
        }
        val alertdialog=builder.create()
        alertdialog.show()
    }
    private fun addcity()
    {
        val builder=AlertDialog.Builder(this)
        val inflater=this.layoutInflater
        val dialogView=inflater.inflate(R.layout.dialog,null)
        val countryname:EditText=dialogView.findViewById(R.id.edit1)
        val cityname:EditText=dialogView.findViewById(R.id.edit2)
        with(builder)
        {
            setView(dialogView)
            setTitle("Add City")
            setPositiveButton("Add"){dialog,which->
                val country=countryname.text.toString()
                val city=cityname.text.toString()
                if(country.isNotBlank() && city.isNotBlank())
                {
                    citydata.add(city(country,city))
                    savecities()

                }
            }
            setNegativeButton("Cancel"){dialog,which->
                //None
            }
        }
        val alertdialog=builder.create()
        alertdialog.show()
    }

}