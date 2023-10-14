package com.shamim.kidsedu.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shamim.kidsedu.R
import com.shamim.kidsedu.model.PlayModel

class PlayAdapter(private val mList: List<PlayModel>, private val onItemEventClickListener: OnItemEventClickListener) : RecyclerView.Adapter<PlayAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context) 
			.inflate(R.layout.play_item, parent, false)

		return ViewHolder(view) 
	} 

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val playModel = mList[position]
		playModel.image?.let { holder.mainImageView.setImageResource(it) }
		holder.autoPlay.setOnClickListener {
			onItemEventClickListener.autoPlay(playModel)
		}
		holder.home.setOnClickListener {
			onItemEventClickListener.home()
		}
		holder.sound.setOnClickListener {
			onItemEventClickListener.music(true)
		}
	}
	override fun getItemCount(): Int { 
		return mList.size 
	} 

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val mainImageView: ImageView = itemView.findViewById(R.id.mainImage)
		val home: ImageView = itemView.findViewById(R.id.homeBtn)
		val autoPlay: ImageView = itemView.findViewById(R.id.autoPlay)
		val sound: ImageView = itemView.findViewById(R.id.soundBtn)

	} 
}
