package com.shamim.kidsedu.view.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shamim.kidsedu.R
import com.shamim.kidsedu.model.MainDataModelItem
import java.io.IOException


class PlayAdapter(private val context: Context,private val mList: List<MainDataModelItem>) : RecyclerView.Adapter<PlayAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context) 
			.inflate(R.layout.play_item, parent, false)

		return ViewHolder(view) 
	} 

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val playModel = mList[position]
		holder.mainImageView.setImageBitmap(loadImageFromAssets(playModel.image_link!!+".png"))
	}
	override fun getItemCount(): Int { 
		return mList.size 
	} 

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val mainImageView: ImageView = itemView.findViewById(R.id.mainImage)
	}

	private fun loadImageFromAssets(fileName: String): Bitmap? {
		val assetManager = context.assets

		return try {
			val inputStream = assetManager.open(fileName)
			BitmapFactory.decodeStream(inputStream)
		} catch (e: IOException) {
			e.printStackTrace()
			null
		}
	}
}
