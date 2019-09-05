package com.example.flickrapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class FlickrDataViewModel : ViewModel() {

    fun getData(): LiveData<ArrayList<FlickerData>> {
        val flickrDataRepository = FlickrDataRepository()
        return flickrDataRepository.getFlickrData()
    }
}