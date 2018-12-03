package com.adi.practiceone

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.View
import com.adi.practiceone.adapter.SliderAdapter
import com.adi.practiceone.model.Product
import com.adi.practiceone.model.ResponDetailProduct
import com.bumptech.glide.Glide
import com.dev.adi.ecosystem.controler.DataRepository
import kotlinx.android.synthetic.main.activity_detail_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class DetailProductActivity : AppCompatActivity() {
    lateinit var product : Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        val extra = intent.extras
        val id = extra.getString("id")
        getDetailProduct(id)
    }

    private fun getDetailProduct(id: String) {
        val service = DataRepository(this).create()
        service.getDetailProduct(id).enqueue(object : Callback<ResponDetailProduct> {
            override fun onResponse(call: Call<ResponDetailProduct>, response: Response<ResponDetailProduct>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        product = body.product
                        title = "Detail Product"
                        tv_name.text = Html.fromHtml(product.name)
                        tv_price.text = convertMoney(product.price)
                        tv_desc.text = Html.fromHtml(product.desc)
                        tv_seller_name.text = product.seller_name
                        tv_feedback.text = "${product.seller_positive_feedback} feedback"
                        tv_city.text = product.city
                        Glide.with(baseContext)
                            .load(product.seller_avatar)
                            .into(ic_seller)
                        tv_stock.text = product.stock.toString()
                        tv_sell.text = product.sold_count.toString()
                        tv_view.text = product.view_count.toString()
                        tv_peminat.text = product.interest_count.toString()
                        banner_slider1.setAdapter(SliderAdapter(product.images, baseContext))
                        checkRating(product.rating.average_rate, product.rating.user_count)
                    }
                }
            }

            override fun onFailure(call: Call<ResponDetailProduct>, t: Throwable) {
            }

        })
    }

    private fun convertMoney (price : Int) : String {
        val kurs = DecimalFormat.getCurrencyInstance() as DecimalFormat
        val format = DecimalFormatSymbols()

        format.currencySymbol = "Rp "
        format.monetaryDecimalSeparator = ','
        format.groupingSeparator = '.'

        kurs.decimalFormatSymbols = format
        return kurs.format(price)
    }

    private fun checkRating (average_rate: Double, user_count : Int) {
        if (average_rate == 0.0 && user_count == 0) {
            layout_rating.visibility = View.GONE
        } else {
            layout_rating.visibility = View.VISIBLE
            tv_rating_num.text = "${user_count} Ulasan"
            ratingBar2.rating = average_rate.toFloat()
        }
    }
}
