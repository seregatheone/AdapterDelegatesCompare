package com.patproject.test.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private fun buildRetrofit():Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun dataService(): DataService = buildRetrofit().create(DataService::class.java)
}