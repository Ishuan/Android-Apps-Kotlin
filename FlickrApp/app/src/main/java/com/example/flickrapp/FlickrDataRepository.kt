package com.example.flickrapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlickrDataRepository {

    val retrofit: Retrofit =
        Retrofit.Builder().baseUrl("https://www.flickr.com/services/feeds/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    val flickrDataWebservice: FlickrDataWebservice =
        retrofit.create(FlickrDataWebservice::class.java)

    fun getFlickrData(): LiveData<ArrayList<FlickerData>> {
        val data = MutableLiveData<ArrayList<FlickerData>>()
        flickrDataWebservice.getFlickrData("json", "en-us", "android,oreo", 1).enqueue(
            object : retrofit2.Callback<Item> {
                override fun onFailure(call: Call<Item>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<Item>, response: Response<Item>) {
                    if (!response.isSuccessful) {
                        return
                    }
                    val itemList: Item? = response.body()
                    data.value = itemList?.items
                }
            })
        return data
    }
}