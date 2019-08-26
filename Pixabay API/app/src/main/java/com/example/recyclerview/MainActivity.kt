package com.example.recyclerview

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), DownloadData.DisplayData {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://pixabay.com/api/".createURL(
            getString(R.string.API_KEY),
            "new+york",
            "photo"
        )
        val downloadData = DownloadData(this)
        downloadData.execute(url)
    }

    override fun displayData(itemList: ArrayList<Item>) {
        recyclerLayoutManager = LinearLayoutManager(this)
        recyclerAdapter = ItemAdapter(itemList)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = recyclerLayoutManager
            adapter = recyclerAdapter
        }
    }

    private fun String.createURL(
        key: String,
        query_string: String,
        image_type: String
    ): String {

        return Uri.parse(this).buildUpon().appendQueryParameter("key", key)
            .appendQueryParameter("q", query_string).appendQueryParameter("image_type", image_type)
            .appendQueryParameter("pretty", "true").build().toString()
    }
}

