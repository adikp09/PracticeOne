package com.adi.practiceone

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log.e
import com.adi.practiceone.Presenter.ListProductPresenter
import com.adi.practiceone.Presenter.ListProductView
import com.adi.practiceone.model.Product

class MainActivity : AppCompatActivity(), ListProductView {

    lateinit var presenter: ListProductPresenter
    var query = "samsung+tab"
    var page = 1

    override fun successGetListProduct(list: List<Product>) {
        e("data", list.toString());
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ListProductPresenter(this, baseContext)
        presenter.getListProduct(query, page)
    }
}
