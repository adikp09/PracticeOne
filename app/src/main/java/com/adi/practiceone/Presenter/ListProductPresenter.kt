package com.adi.practiceone.Presenter

import android.content.Context
import com.adi.practiceone.model.Product
import com.adi.practiceone.model.ResponListProduct
import com.dev.adi.ecosystem.controler.DataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListProductPresenter (val view: ListProductView, val context: Context) {

    fun getListProduct(query : String, page : Int) {
        val service = DataRepository(context).create()
        service.getListProduct().enqueue(object : Callback<ResponListProduct> {
            override fun onResponse(call: Call<ResponListProduct>, response: Response<ResponListProduct>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        view.successGetListProduct(body.products)
                    }
                }
            }

            override fun onFailure(call: Call<ResponListProduct>, t: Throwable) {
            }

        })
    }
}


interface ListProductView {
    fun successGetListProduct(list: List<Product>)
}