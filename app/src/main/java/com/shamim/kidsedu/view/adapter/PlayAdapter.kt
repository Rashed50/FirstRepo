package com.shamim.kidsedu.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.shamim.kidsedu.R
import com.shamim.kidsedu.model.PlayModel

class PlayAdapter(private val mList: List<PlayModel>) : RecyclerView.Adapter<PlayAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context) 
			.inflate(R.layout.play_item, parent, false)

		return ViewHolder(view) 
	} 

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val playModel = mList[position]
		playModel.image?.let { holder.mainImageView.setImageResource(it) }

	}
	override fun getItemCount(): Int { 
		return mList.size 
	} 

	class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val mainImageView: ImageView = itemView.findViewById(R.id.mainImage)
	} 
}
