package com.example.myapplication3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


 class CountryAdapter(private val listener:(country)->Unit):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    var countrydata= listOf<country>()
    set(value)
    {
        field=value
            notifyDataSetChanged()
    }

    inner class CountryViewHolder(view : View): RecyclerView.ViewHolder(view){
             val countryFlag: ImageView=view.findViewById(R.id.countryflag)
             val countryName: TextView=view.findViewById(R.id.countryname)
             val countryCapital: TextView=view.findViewById(R.id.countrycapital)
        init{
            itemView.setOnClickListener {
                listener.invoke(countrydata[adapterPosition])
            }
        }


    }
    override fun getItemCount(): Int {
        return countrydata.size
    }

    //important
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {

        val itemlayout=LayoutInflater.from(parent.context).inflate(R.layout.country_images_for_recycleview,parent,false)
        return CountryViewHolder(itemlayout)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
       with(holder){
           countryFlag.setImageResource(countrydata[position].flagid)
           countryName.text=countrydata[position].name
           countryCapital.text=countrydata[position].capitalcity

       }
    }
    //optional
    override fun getItemId(position: Int): Long {
        return countrydata[position].code.hashCode().toLong()
    }
}