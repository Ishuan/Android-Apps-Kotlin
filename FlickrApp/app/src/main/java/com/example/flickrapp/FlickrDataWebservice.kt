package com.example.flickrapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrDataWebservice {

    @GET("photos_public.gne")
    fun getFlickrData(
        @Query("format") format: String,
        @Query("lang") language: String,
        @Query("tags") tags: String,
        @Query("nojsoncallback") noJsonCallBack: Int
    ): Call<Item>
}