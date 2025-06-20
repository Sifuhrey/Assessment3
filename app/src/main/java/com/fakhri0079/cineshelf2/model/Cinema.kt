package com.fakhri0079.cineshelf2.model

data class Cinema(
    val id: Int,
    val userId: String,
    val title: String,
    val description: String,
    val rating: Float,
    val isWatched: Boolean,
    val imageUrl: String
)
