package com.shamim.kidsedu.view

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.shamim.kidsedu.R
import com.shamim.kidsedu.databinding.ActivityPlayBinding
import com.shamim.kidsedu.model.PlayModel
import com.shamim.kidsedu.view.adapter.OnItemEventClickListener
import com.shamim.kidsedu.view.adapter.PlayAdapter


class PlayActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var _binding: ActivityPlayBinding

    private lateinit var adapter: PlayAdapter
    private lateinit var playModel: PlayModel

    private var currentPlayingPosition = 0
    private var isSound = false
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

        adapter = PlayAdapter(data)

        _binding.playRecyclerview.adapter = adapter

        val layoutManager: LinearLayoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }

            override fun canScrollHorizontally(): Boolean {
                return true
            }
        }

        _binding.playRecyclerview.layoutManager = layoutManager

        _binding.previewBtn.setOnClickListener {
            if (layoutManager.findLastCompletelyVisibleItemPosition() < (adapter.itemCount + 1)) {
                layoutManager.scrollToPosition(layoutManager.findLastCompletelyVisibleItemPosition() - 1)
                currentPlayingPosition--
                if (data[currentPlayingPosition].audio != null) {
                    mediaPlayer = MediaPlayer.create(this, data[currentPlayingPosition].audio!!)
                    mediaPlayer?.start()
                }
            }

        }
        _binding.nextBtn.setOnClickListener {
            if (layoutManager.findLastCompletelyVisibleItemPosition() < (adapter.itemCount - 1)) {
                layoutManager.scrollToPosition(layoutManager.findLastCompletelyVisibleItemPosition() + 1)
                currentPlayingPosition++
                if (data[currentPlayingPosition].audio != null) {
                    mediaPlayer = MediaPlayer.create(this, data[currentPlayingPosition].audio!!)
                    mediaPlayer?.start()
                }
            }

        }

        _binding.soundBtn.setOnClickListener {
            if (isSound){
                isSound = false
                music(isSound)
            }
            else{
                isSound = true
                music(isSound)
            }

        }

        _binding.homeBtn.setOnClickListener {
            finish()
        }
    }

    fun autoPlay(playModel: PlayModel) {

        mediaPlayer = MediaPlayer.create(this, playModel.audio!!)
        mediaPlayer?.start()
        mediaPlayer?.setOnPreparedListener {

            val duration = mediaPlayer?.duration
            val durationInSeconds = (duration?.div(1000))
            Log.d("audio", durationInSeconds.toString())

        }

    }

    private fun music(isSound: Boolean) {
        if (isSound){
            Toast.makeText(this, "1111111", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "000000", Toast.LENGTH_SHORT).show()
        }
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