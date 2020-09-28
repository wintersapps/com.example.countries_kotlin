package com.example.countries_kotlin.di

import com.example.countries_kotlin.model.CountriesApi
import com.example.countries_kotlin.model.CountriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val baseUrl = "https://raw.githubusercontent.com"

    @Provides
    fun provideCountriesApi(): CountriesApi{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    fun provideCountriesService(): CountriesService{
        return CountriesService()
    }
}