package com.example.recyclerview

class Item(val itemImageURL : String, val itemAuthor : String, val itemDownloads: String) {

    override fun toString(): String {
        return "Item(itemImageURL=$itemImageURL, itemAuthor=$itemAuthor, itemDownloads=$itemDownloads)"
    }


}

