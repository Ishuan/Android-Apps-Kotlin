package com.example.flickrapp

data class FlickerData(
    val title: String,
    val author: String,
    val media: Media
)

data class Media(val m: String)

data class Item(val items: ArrayList<FlickerData>)