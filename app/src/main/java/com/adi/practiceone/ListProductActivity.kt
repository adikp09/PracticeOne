package com.adi.practiceone

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import com.adi.practiceone.adapter.ListProductAdapter
import com.adi.practiceone.model.Product
import com.adi.practiceone.model.ResponListProduct
import com.dev.adi.ecosystem.controler.DataRepository
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListProductActivity : AppCompatActivity(), ListProductAdapter.onClickListener {

    var query = "samsung"
    var page = 1
    lateinit var adapter: ListProductAdapter
    private val listProduct: ArrayList<Product> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "List Product"

        init()
        getListProduct()
        product_list.addOnScrollListener(scrollData())

        et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                var q = et_search.text.toString()
                page = 1
                query = q.replace(" ", "+")
                getListProduct()
                listProduct.clear()
                adapter.notifyDataSetChanged()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    override fun onClickItem(id: String) {
        val intent = Intent(this, DetailProductActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun scrollData(): EndlessOnScrollListener {
        return object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                getListProduct()
                progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun init () {
        adapter = ListProductAdapter(listProduct, this, this)
        product_list.layoutManager = GridLayoutManager(this, 1)
        product_list.itemAnimator = DefaultItemAnimator()
        product_list.adapter = adapter
    }


    private fun getListProduct() {
        val service = DataRepository(this).create()
        service.getListProduct(query, page).enqueue(object : Callback<ResponListProduct> {
            override fun onResponse(call: Call<ResponListProduct>, response: Response<ResponListProduct>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        listProduct.addAll(body.products)
                        adapter.notifyDataSetChanged()
                        progressBar.visibility = View.GONE
                        page++
                    }
                }
            }

            override fun onFailure(call: Call<ResponListProduct>, t: Throwable) {
            }

        })
    }
}
