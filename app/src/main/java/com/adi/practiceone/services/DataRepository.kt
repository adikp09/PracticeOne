package com.dev.adi.ecosystem.controler

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class DataRepository(val context: Context) {
    fun create(): Services {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
//            .addInterceptor( ChuckInterceptor(context))
            .addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://api.bukalapak.com/")
                .build()
        return retrofit.create(Services::class.java)
    }
}