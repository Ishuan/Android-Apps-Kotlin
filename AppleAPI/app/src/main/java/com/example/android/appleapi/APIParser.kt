package com.example.android.appleapi

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class APIParser {

    val applicationInfo = ArrayList<APIDetails>()

    fun parseXML(xmlData: String): ArrayList<APIDetails> {

        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val parser = factory.newPullParser()
        parser.setInput(xmlData.reader())
        var event = parser.eventType
        var textValue = ""
        var currentAPI = APIDetails()
        while (event != XmlPullParser.END_DOCUMENT) {
            val tagText = parser.name?.toLowerCase()
            when (event) {
                XmlPullParser.START_TAG -> {
                    if (tagText == "entry") {
                        currentAPI = APIDetails()
                    }
                }
                XmlPullParser.TEXT -> textValue = parser.text
                XmlPullParser.END_TAG -> {
                    when (tagText) {
                        "entry" -> applicationInfo.add(currentAPI)
                        "title" -> currentAPI.title = textValue
                        "rights" -> currentAPI.rights = textValue
                    }
                }
            }
            event = parser.next()
        }
        return applicationInfo
    }
}