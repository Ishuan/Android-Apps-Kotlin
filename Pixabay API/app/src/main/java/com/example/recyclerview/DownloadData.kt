package com.example.recyclerview

import android.os.AsyncTask
import org.json.JSONException
import org.json.JSONObject
import java.net.URL

class DownloadData(private val listener:DisplayData) : AsyncTask<String, Void, String>() {

    private var itemList = ArrayList<Item>()

    interface DisplayData{
        fun displayData(itemList:ArrayList<Item>)
    }

    override fun onPostExecute(result: String) {
        try {
            val jsonObject = JSONObject(result)
            val jsonArray = jsonObject.getJSONArray("hits")
            for (i in 0 until jsonArray.length()) {
                val jsonSubObject = jsonArray.getJSONObject(i)
                val item = Item(jsonSubObject.getString("webformatURL"),
                    jsonSubObject.getString("user"),jsonSubObject.getString("downloads"))
                itemList.add(item)
            }
            listener.displayData(itemList)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    override fun doInBackground(vararg p0: String?): String {
        return URL(p0[0]).readText()
    }
}