package com.example.android.appleapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ViewHolder(v: View) {
    val title: TextView = v.findViewById(R.id.titleInfo)
    val rights: TextView = v.findViewById(R.id.rightsInfo)
}

class APIAdapter(context: Context, private val resource: Int, private val apiData: ArrayList<APIDetails>) :
    ArrayAdapter<APIDetails>(context, resource, apiData) {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(resource, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val currentAPI = apiData[position]

        viewHolder.title.text = currentAPI.title
        viewHolder.rights.text = currentAPI.rights

        return view
    }
}