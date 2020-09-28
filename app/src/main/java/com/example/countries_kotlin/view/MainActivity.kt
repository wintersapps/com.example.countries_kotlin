package com.example.countries_kotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries_kotlin.R
import com.example.countries_kotlin.view.adapters.CountryListAdapter
import com.example.countries_kotlin.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.countries.observe(this, { countries ->
            recyclerView.visibility = View.VISIBLE
            countries?.let { countriesAdapter.updateCountries(it) }
        })

        viewModel.countryLoadError.observe(this, { isError ->
            isError?.let { listErrorMaterialTextView.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, { loading ->
            loading?.let {
                loadingDataProgressBar.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    listErrorMaterialTextView.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
            }
        })
    }
}