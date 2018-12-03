package com.dev.adi.ecosystem.controler

import com.adi.practiceone.model.ResponDetailProduct
import com.adi.practiceone.model.ResponListProduct
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

    //1
    @GET("/v2/products.json")
    fun getListProduct(
        @Query("keywords") query: String,
        @Query("page") page: Int
    ): Call<ResponListProduct>


    //2
    @GET("/v2/products/{id}.json")
    fun getDetailProduct(
        @Path("id") id: String
    ): Call<ResponDetailProduct>

}