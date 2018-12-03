package com.adi.practiceone.adapter

import android.content.Context
import com.bumptech.glide.Glide
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder


class SliderAdapter (private val list: List<String>, private val context: Context) : SliderAdapter() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder) {
        Glide.with(context)
            .load(list[position])
            .into(viewHolder.imageView)
    }
}