package com.shamim.kidsedu.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import com.shamim.kidsedu.R
import com.shamim.kidsedu.model.GridViewModal
import com.shamim.kidsedu.view.adapter.GridRVAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var homeGridview: GridView
    private lateinit var homeGridList: GridViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeGridview = findViewById(R.id.idGRV)
        homeGridList = GridViewModal()

        val gridRVAdapter = GridRVAdapter(homeGridList.getDataList(), this@MainActivity)

        homeGridview.adapter = gridRVAdapter

        homeGridview.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this,PlayActivity::class.java)
            intent.putExtra("key", position+1)
            startActivity(intent)
        }
    }
}