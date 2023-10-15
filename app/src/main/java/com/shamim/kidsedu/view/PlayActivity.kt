package com.shamim.kidsedu.view

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.shamim.kidsedu.R
import com.shamim.kidsedu.databinding.ActivityPlayBinding
import com.shamim.kidsedu.model.PlayModel
import com.shamim.kidsedu.view.adapter.OnItemEventClickListener
import com.shamim.kidsedu.view.adapter.PlayAdapter


class PlayActivity : AppCompatActivity(), OnItemEventClickListener {
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var _binding: ActivityPlayBinding

    private lateinit var adapter: PlayAdapter
    private lateinit var playModel: PlayModel

    var currentPlayingPosition = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_play)

        val category: String = intent.getStringExtra("key").toString()

        playModel = PlayModel()
        var data = ArrayList<PlayModel>()
        data.clear()
        data = playModel.playDataList() as ArrayList<PlayModel>

        adapter = PlayAdapter(data, this)

        _binding.playRecyclerview.adapter = adapter

        val layoutManager: LinearLayoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }

            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }
        _binding.playRecyclerview.layoutManager = layoutManager
    }

    override fun home() {
        finish()
    }

    override fun autoPlay(playModel: PlayModel, position: Int) {


        val isPlaying = position == currentPlayingPosition

        mediaPlayer = MediaPlayer.create(this, playModel.audio!!)
        mediaPlayer?.start()
//        if (isPlaying) {
//            mediaPlayer?.start()
//        } else {
//          stopMedia()
//        }
        mediaPlayer?.setOnPreparedListener {

            val duration = mediaPlayer?.duration
            val durationInSeconds = (duration?.div(1000))
            Log.d("audio", durationInSeconds.toString())

        }


    }

    override fun music(isOn: Boolean) {

    }

    override fun preview() {

    }

    override fun next() {

    }

    private fun stopMedia() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer != null) {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}