package com.example.android.youtubeplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSingleVideo.setOnClickListener(this)
        btnStandAlone.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this,StandAlone::class.java)
        when(v?.id){
            R.id.btnSingleVideo -> Toast.makeText(this,"Video not yet implemented",
                Toast.LENGTH_SHORT).show()
            R.id.btnStandAlone -> startActivity(intent)
        }
    }
}
