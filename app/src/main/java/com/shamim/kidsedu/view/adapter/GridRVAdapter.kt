package com.shamim.kidsedu.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.shamim.kidsedu.R
import com.shamim.kidsedu.model.GridViewModal


internal class GridRVAdapter(

	private val modalList: List<GridViewModal>,
	private val context: Context
) :
	BaseAdapter() {

	private var layoutInflater: LayoutInflater? = null
	private lateinit var titleTv: TextView
	private lateinit var imageView: ImageView

	// below method is use to return the count of course list
	override fun getCount(): Int {
		return modalList.size
	}

	// below function is use to return the item of grid view.
	override fun getItem(position: Int): Any? {
		return null
	}
	
	// below function is use to return item id of grid view.
	override fun getItemId(position: Int): Long {
		return 0
	}

	// in below function we are getting individual item of grid view.
	override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
		var convertView = convertView
		// on blow line we are checking if layout inflater 
		// is null, if it is null we are initializing it.
		if (layoutInflater == null) {
			layoutInflater =
				context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		}
		// on the below line we are checking if convert view is null. 
		// If it is null we are initializing it.
		if (convertView == null) {
			// on below line we are passing the layout file
			// which we have to inflate for each item of grid view.
			convertView = layoutInflater!!.inflate(R.layout.home_item, null)
		}
		// on below line we are initializing our course image view 
		// and course text view with their ids.
		imageView = convertView!!.findViewById(R.id.imageView)
		//titleTv = convertView.findViewById(R.id.titleTv)
		imageView.setImageResource(modalList[position].image!!)
		// on below line we are setting text in our course text view.
		//titleTv.text = modalList[position].title!!
		// at last we are returning our convert view.
		return convertView
	}




}
