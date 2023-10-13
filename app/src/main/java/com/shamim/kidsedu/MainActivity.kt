package com.shamim.kidsedu

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
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

            Toast.makeText(
                applicationContext, homeGridList.getDataList()[position].title + " selected",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}