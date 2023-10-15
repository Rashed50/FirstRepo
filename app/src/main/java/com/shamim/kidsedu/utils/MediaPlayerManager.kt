package com.shamim.kidsedu.utils

import android.media.MediaPlayer
import java.io.IOException

class MediaPlayerManager {
    private var mediaPlayer: MediaPlayer? = null
    private var currentPlayingPosition = -1

    fun playMedia(mediaUrl: Int) {
        try {
            mediaPlayer?.let {
                it.stop()
                it.release()
            }

            mediaPlayer = MediaPlayer().apply {
                mediaUrl
                prepare()
                start()
                setOnCompletionListener {
                    stopMedia()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun stopMedia() {
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
        mediaPlayer = null
    }

    fun setCurrentPlayingPosition(position: Int) {
        currentPlayingPosition = position
    }
}
