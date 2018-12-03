package com.adi.practiceone.model

data class Category(
    val children: List<Children>,
    val count: Int,
    val id: Int,
    val name: String,
    val permalink: String
)