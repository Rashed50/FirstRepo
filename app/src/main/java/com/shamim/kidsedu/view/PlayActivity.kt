package com.shamim.kidsedu.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.shamim.kidsedu.R
import com.shamim.kidsedu.databinding.ActivityPlayBinding
import com.shamim.kidsedu.model.PlayModel
import com.shamim.kidsedu.view.adapter.OnItemEventClickListener
import com.shamim.kidsedu.view.adapter.PlayAdapter

class PlayActivity : AppCompatActivity(), OnItemEventClickListener {

    private lateinit var _binding: ActivityPlayBinding

    private lateinit var adapter: PlayAdapter
    private lateinit var playModel: PlayModel
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
    }

    override fun home() {
        finish()
    }

    override fun autoPlay(playModel: PlayModel) {

    }

    override fun music(isOn: Boolean) {

    }
}