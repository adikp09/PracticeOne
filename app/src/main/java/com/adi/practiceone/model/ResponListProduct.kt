package com.adi.practiceone.model

data class ResponListProduct(
    val categories: List<Category>,
    val products: List<Product>,
    val recommended_products: List<Any>,
    val status: String
)