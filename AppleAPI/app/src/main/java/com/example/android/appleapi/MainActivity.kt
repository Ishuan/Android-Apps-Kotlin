package com.example.android.appleapi

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private var downloadAPIData: DownloadAPIData? = null

    private var feedURL = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=%d/xml"
    private var feedLimit = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val downloadAPIData = DownloadAPIData(this, listView)
        downloadAPIData.execute(feedURL.format(feedLimit))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.floating_menu, menu)
        return true
    }

    private fun downloadData(feedURL: String) {
        val downloadAPIData = DownloadAPIData(this, listView)
        downloadAPIData.execute(feedURL)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.mnuAlbums ->
                feedURL = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topalbums/limit=%d/xml"
            R.id.mnuSongs ->
                feedURL = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=%d/xml"
            R.id.mnu_top10 -> {
                if (!item.isChecked) {
                    item.isChecked = true
                    feedLimit = 10
                }
            }
            R.id.mnu_top25 -> if (!item.isChecked) {
                item.isChecked = true
                feedLimit = 25
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
        downloadData(feedURL.format(feedLimit))
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        downloadAPIData?.cancel(true)
    }

    companion object {
        private class DownloadAPIData(context: Context, listView: ListView) : AsyncTask<String, Void, String>() {

            var propContext: Context by Delegates.notNull()
            var propListView: ListView by Delegates.notNull()

            init {
                propContext = context
                propListView = listView
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                val apiParser = APIParser()
                apiParser.parseXML(result)
                val apiAdapter = APIAdapter(propContext, R.layout.api_data_layout, apiParser.applicationInfo)
                propListView.adapter = apiAdapter
            }

            override fun doInBackground(vararg p0: String?): String {
                return URL(p0[0]).readText()
            }
        }
    }
}