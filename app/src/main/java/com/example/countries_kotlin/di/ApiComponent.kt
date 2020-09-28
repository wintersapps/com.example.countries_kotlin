package com.example.countries_kotlin.di

import com.example.countries_kotlin.model.CountriesService
import com.example.countries_kotlin.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)

    fun inject(viewModel: ListViewModel)
}