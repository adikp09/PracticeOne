package com.dev.adi.ecosystem.controler

import com.adi.practiceone.model.ResponDetailProduct
import com.adi.practiceone.model.ResponListProduct
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Services {

    //1
    @GET("/v2/products.json?keywords=samsung&page=1")
    fun getListProduct(
//        @Path("query") query: String,
//        @Path("page") page: Int
    ): Call<ResponListProduct>


    //2
    @GET("/v2/products/{id}.json")
    fun getDetailProduct(
        @Path("id") id: String
    ): Call<ResponDetailProduct>

}