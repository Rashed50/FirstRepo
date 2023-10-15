package com.shamim.kidsedu.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
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
			onItemEventClickListener.autoPlay(playModel,position)
		}
		holder.home.setOnClickListener {
			onItemEventClickListener.home()
		}
		holder.sound.setOnClickListener {
			onItemEventClickListener.music(true)
		}

		holder.previrewBtn.setOnClickListener {

		}
		holder.nextBtn.setOnClickListener {

		}
	}
	override fun getItemCount(): Int { 
		return mList.size 
	} 

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val mainLayout: ConstraintLayout = itemView.findViewById(R.id.mainLayout)
		val mainImageView: ImageView = itemView.findViewById(R.id.mainImage)
		val home: ImageView = itemView.findViewById(R.id.homeBtn)
		val autoPlay: ImageView = itemView.findViewById(R.id.autoPlay)
		val sound: ImageView = itemView.findViewById(R.id.soundBtn)
		val previrewBtn: ImageView = itemView.findViewById(R.id.previewBtn)
		val nextBtn: ImageView = itemView.findViewById(R.id.nextBtn)

	} 
}
