package com.example.myapplication3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CityAdapter(val citydata: Array<city>):BaseAdapter() {
    override fun getCount(): Int {
        return citydata.size
    }

    override fun getItem(position: Int): city {
        return citydata[position]
    }

    override fun getItemId(position: Int): Long {
        return citydata[position].hashCode().toLong()
    }

    // important
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val cityView: View
        val viewHolder: viewHolder
        if(convertView==null)
        {
            cityView=LayoutInflater.from(parent?.context).inflate(R.layout.activity_main4,parent,false)
            viewHolder=viewHolder()
            with(viewHolder){
                citycountry=cityView.findViewById(R.id.country)
                citycapital=cityView.findViewById(R.id.capital)
                cityView.tag=this  //this is key step
            }
        }
        else{
            cityView=convertView
            viewHolder=cityView.tag as viewHolder
        }

        with(viewHolder)
        {
            val city=getItem(position)
            citycountry.text=city.country
            citycapital.text=city.capital
            if(city.is_selected)
            {
                cityView.setBackgroundColor(ContextCompat.getColor(cityView.context,android.R.color.holo_orange_dark))
            }
            else{
                cityView.setBackgroundColor(ContextCompat.getColor(cityView.context,android.R.color.white))
            }
        }

        return cityView
    }
    private class viewHolder
    {
        lateinit var citycountry:TextView
        lateinit var citycapital:TextView
    }

}