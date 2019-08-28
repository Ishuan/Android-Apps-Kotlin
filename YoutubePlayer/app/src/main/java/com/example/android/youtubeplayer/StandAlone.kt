package com.example.android.youtubeplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_stand_alone.*

class StandAlone : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stand_alone)

        btnPlayVideo.setOnClickListener(this)
        btnPlaylist.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnPlayVideo -> {
                val intent = YouTubeStandalonePlayer.createVideoIntent(this,
                    R.string.GOOGLE_API_KEY.toString(), GOD_OF_WAR_VIDEO)
                startActivity(intent)
            }
            R.id.btnPlaylist -> {
                startActivity(YouTubeStandalonePlayer.createPlaylistIntent(this,
                    R.string.GOOGLE_API_KEY.toString(), GOD_OF_WAR_PLAYLIST))
            }
        }

    }
}
