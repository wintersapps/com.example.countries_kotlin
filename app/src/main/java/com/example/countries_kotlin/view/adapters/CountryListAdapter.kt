package com.example.countries_kotlin.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries_kotlin.R
import com.example.countries_kotlin.model.Country
import com.example.countries_kotlin.util.getProgressDrawable
import com.example.countries_kotlin.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {

    fun updateCountries(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount() = countries.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val imageView = view.appCompatImageView
        private val countryName = view.nameMaterialTextView
        private val capital = view.capitalMaterialTextView
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country){
            countryName.text = country.countryName
            capital.text = country.capital
            imageView.loadImage(country.flag, progressDrawable)
        }
    }
}