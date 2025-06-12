package com.fakhri0079.cineshelf2.model

data class CinemaResponse(
    val status: Int,
    val message: String,
    val cinema: List<Cinema>
)
