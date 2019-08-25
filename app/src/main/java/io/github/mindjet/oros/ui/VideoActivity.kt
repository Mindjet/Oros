package io.github.mindjet.oros.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.MediaController
import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.R
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val videoUrl = intent.getStringExtra(Constant.VIDEO_URL)
        val videoTitle = intent.getStringExtra(Constant.VIDEO_TITLE)

        tv_title.text = videoTitle

        video_view.apply {
            setVideoPath(videoUrl)
            setMediaController(MediaController(context))
            setOnPreparedListener { start(); pb_loading.visibility = View.GONE }
            setOnCompletionListener { start() }
        }

    }

}