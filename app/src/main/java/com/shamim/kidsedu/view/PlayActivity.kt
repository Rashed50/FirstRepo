package com.shamim.kidsedu.view

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.shamim.kidsedu.R
import com.shamim.kidsedu.databinding.ActivityPlayBinding
import com.shamim.kidsedu.model.MainDataModelItem
import com.shamim.kidsedu.view.adapter.PlayAdapter
import java.io.IOException
import java.text.BreakIterator
import java.util.Timer
import java.util.TimerTask


class PlayActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var _binding: ActivityPlayBinding

    private lateinit var adapter: PlayAdapter

    private var currentPlayingPosition = 0
    private var isSound = false
    private var isAutoPlay = true
    private val data = ArrayList<MainDataModelItem>()
    private var timer : Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_play)

        timer = Timer()

        val category: Int = intent.getIntExtra("key", 0)

        data.clear()

        //Read Category Data
        for (i in getCategoryData(this).iterator()) {
            if (i.category_id == category) {
                data.add(i)
            }
        }

        Log.d("data", data.toString())
        Log.d("data", category.toString())

        adapter = PlayAdapter(this, data)

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
            if (currentPlayingPosition > 0){
                if (layoutManager.findLastCompletelyVisibleItemPosition() < (adapter.itemCount)) {
                    layoutManager.scrollToPosition(layoutManager.findLastCompletelyVisibleItemPosition() - 1)
                    currentPlayingPosition--
                    Log.d("ddd", currentPlayingPosition.toString())

                    if (data[currentPlayingPosition].image_sound != null) {
                        val audio = data[currentPlayingPosition].image_sound + ".mp3"
                        startSound(audio)

                    }
//                    if (currentPlayingPosition != -1 && currentPlayingPosition != -2 && currentPlayingPosition != -3 && currentPlayingPosition != -4 && currentPlayingPosition != -5
//                        && currentPlayingPosition != -6 && currentPlayingPosition != -7 && currentPlayingPosition != -8 && currentPlayingPosition != -9
//                    ) {
//
//                    }
                    Log.d("ddd", adapter.itemCount.toString())
                }
            }


        }
        _binding.nextBtn.setOnClickListener {

            try {
                if (layoutManager.findLastCompletelyVisibleItemPosition() < (adapter.itemCount - 1)) {
                    layoutManager.scrollToPosition(layoutManager.findLastCompletelyVisibleItemPosition() + 1)
                    currentPlayingPosition++
                    if (!currentPlayingPosition.toString().contains("-")){
                        if (data[currentPlayingPosition].image_sound != null) {
                            val audio = data[currentPlayingPosition].image_sound + ".mp3"
                            startSound(audio)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        _binding.soundBtn.setOnClickListener {
            if (isSound) {
                isSound = false
                music(false)
            } else {
                isSound = true
                music(true)
            }

        }

        _binding.homeBtn.setOnClickListener {
            finish()
        }

        var m = 1
        _binding.autoPlay.setOnClickListener {
            if (m ==1){

                val layoutManager1: LinearLayoutManager = object : LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false){
                    override fun canScrollVertically(): Boolean {
                        return false
                    }

                    override fun canScrollHorizontally(): Boolean {
                        return true
                    }
                }
                _binding.playRecyclerview.layoutManager = layoutManager1

                _binding.autoPlay.setImageResource(R.drawable.slideshowon)
                Toast.makeText(this, "1111111", Toast.LENGTH_SHORT).show()

                timer?.schedule(object : TimerTask() {
                    override fun run() {
                        if ((_binding.playRecyclerview.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() < adapter.itemCount-1) {

                            (_binding.playRecyclerview.layoutManager as LinearLayoutManager).smoothScrollToPosition(
                                _binding.playRecyclerview, RecyclerView.State(),
                                (_binding.playRecyclerview.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() + 1
                            )

                            if (data[currentPlayingPosition].image_sound != null) {
                                val audio = data[currentPlayingPosition].image_sound + ".mp3"
                                startSound(audio)
                                currentPlayingPosition++
                            }
                        }
                        Log.d("ddd", "Auto Play $currentPlayingPosition")
                    }
                }, 0, 3000)
            }
            m = 0

        }

    }


    private fun startSound(filename: String) {
        var afd: AssetFileDescriptor? = null
        try {
            afd = resources.assets.openFd(filename)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val player = MediaPlayer()
        try {
            if (afd != null) {
                player.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
        try {
            player.prepare()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        player.start()
    }

    private fun music(isSound: Boolean) {
        if (isSound) {
            Toast.makeText(this, "1111111", Toast.LENGTH_SHORT).show()
        } else {
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


    private fun getCategoryData(context: Context): List<MainDataModelItem> {

        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("gameJsonData.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d("Error", ioException.toString())
        }

        val listCategoryType = object : TypeToken<List<MainDataModelItem>>() {}.type
        return Gson().fromJson(jsonString, listCategoryType)
    }

    override fun onStop() {
        stopMedia()
        timer?.cancel()
        currentPlayingPosition = 0
        super.onStop()
    }
}