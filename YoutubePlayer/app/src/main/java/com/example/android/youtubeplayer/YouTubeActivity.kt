package com.example.android.youtubeplayer

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val GOD_OF_WAR_VIDEO = "oUMRwOAa-BY"
const val GOD_OF_WAR_PLAYLIST = "PLs1-UdHIwbo53L1KAkOZMvUSGHv9dAxYT"

class YouTubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_you_tube)
        val constLayout = findViewById<ConstraintLayout>(R.id.youTubeLayout)*/

        val constLayout = layoutInflater.inflate(R.layout.activity_you_tube,null) as ConstraintLayout
        setContentView(constLayout)

        val youTubePlayerView = YouTubePlayerView(this)
        youTubePlayerView.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        constLayout.addView(youTubePlayerView)

        youTubePlayerView.initialize(getString(R.string.GOOGLE_API_KEY),this)
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        if(!p2)
            p1?.loadVideo(GOD_OF_WAR_VIDEO)
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
       val REQ_CODE = 0
        if(p1?.isUserRecoverableError == true){
            p1?.getErrorDialog(this,REQ_CODE).show()
        }else{
            val errorMsg = "Error : $p1"
            Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show()
        }
    }
}
