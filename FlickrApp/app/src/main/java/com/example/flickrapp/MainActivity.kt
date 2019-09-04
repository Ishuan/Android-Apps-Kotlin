package com.example.flickrapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val model = ViewModelProviders.of(this)[FlickrDataViewModel::class.java]
        model.getData().observe(this, Observer<ArrayList<FlickerData>> {
            for(item in it){
                Log.d("MainActivity","***************")
                Log.d("MainActivity",item.title)
                Log.d("MainActivity",item.author)
                Log.d("MainActivity",item.media.m)
            }
        })
    }
}
