package com.example.flickrapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of(this)[FlickrDataViewModel::class.java]
        model.getData().observe(this, Observer<ArrayList<FlickerData>> {
            recyclerLayoutManager = LinearLayoutManager(this)
            recyclerAdapter = Adapter(it)

            recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
                setHasFixedSize(true)
                layoutManager = recyclerLayoutManager
                adapter = recyclerAdapter
            }
        })
    }
}
