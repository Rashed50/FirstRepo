package com.shamim.kidsedu.model

import com.shamim.kidsedu.R

data class GridViewModal(
    var image: Int? = null
) {
    private var dataList = ArrayList<GridViewModal>()

    // ["write.png","alphabets.png", "animals.png", "bathroom.png", "birds.png", "bodyparts.png", "color.png", "day.png", "dress.png", "electronics.png","fish.png","flowers.png","fruits.png","furniture.png","insects.png","kitchen.png","maps.png","month.png","numbers.png","shapes.png","solarsystem.png","sports.png","vegetables.png","vehicles.png","vitamin.png","mathsign.png"]
    fun getDataList(): List<GridViewModal> {
        val data = listOf<Int>(R.drawable.write, R.drawable.alphabets, R.drawable.animals)
        dataList.clear()
        data.forEach { mData ->
            dataList.add(GridViewModal(image = mData))
        }

        return dataList
    }
}