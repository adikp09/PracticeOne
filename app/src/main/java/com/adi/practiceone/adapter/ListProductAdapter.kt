package com.adi.practiceone.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adi.practiceone.R
import com.adi.practiceone.model.Product
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_product.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class ListProductAdapter (private val list: MutableList<Product>, private val onClick: onClickListener, private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_product, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(context)
            .load(list[position].images[0])
            .into(holder.itemView.ic_product)
        holder.itemView.txt0.text = list[position].name
        holder.itemView.txt1.text = convertMoney(list[position].price)
        holder.itemView.txt3.text = list[position].seller_name
        holder.itemView.txt5.text = list[position].city
        holder.itemView.txt4.text = "${list[position].seller_positive_feedback} feedback"
        holder.itemView.setOnClickListener { onClick.onClickItem(list[position].id) }
        checkRating(holder, position)

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

    private fun checkRating (holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].rating.average_rate == 0.0 && list[position].rating.user_count == 0) {
            holder.itemView.rating_layout.visibility = View.GONE
        } else {
            holder.itemView.rating_layout.visibility = View.VISIBLE
            holder.itemView.ratingBar2.rating = list[position].rating.average_rate.toFloat()
            holder.itemView.tv_rating_num.text = "(${list[position].rating.user_count})"
        }
    }

    interface onClickListener {
        fun onClickItem(id: String)
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView)
}