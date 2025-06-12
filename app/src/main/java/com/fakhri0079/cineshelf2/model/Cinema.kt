package com.fakhri0079.cineshelf2.model

data class Cinema(
    val id: String,
    val userId: String,
    val title: String,
    val description: String,
    val rating: String,
    val isWatched: Boolean,
    val imageUrl: String
)
