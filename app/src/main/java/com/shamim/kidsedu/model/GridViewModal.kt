package com.shamim.kidsedu.model

import com.shamim.kidsedu.R

data class GridViewModal(
    var image: Int? = null
) {
    private var dataList = ArrayList<GridViewModal>()


    fun getDataList(): List<GridViewModal> {
        val data = listOf<Int>(
//            R.drawable.write,
            R.drawable.alphabets,
            R.drawable.animals,
            R.drawable.bathroom,
            R.drawable.birds,
            R.drawable.bodyparts,

            R.drawable.color,
            R.drawable.day,
            R.drawable.dress,
            R.drawable.electronics,
            R.drawable.fish,
            R.drawable.flowers,

            R.drawable.fruits,
            R.drawable.furniture,
            R.drawable.insects,
            R.drawable.kitchen,
            R.drawable.maps,

            R.drawable.month,
            R.drawable.numbers,
            R.drawable.shapes,
            R.drawable.solarsystem,
            R.drawable.sports,

            R.drawable.vegetables,
            R.drawable.vehicles,
            R.drawable.vitamin,
            R.drawable.mathsign,

            )
        // ["write.png","alphabets.png", "animals.png", "bathroom.png", "birds.png",
        // "bodyparts.png", "color.png", "day.png", "dress.png", "electronics.png",
        // "fish.png","flowers.png","fruits.png","furniture.png","insects.png","kitchen.png",
        // "maps.png","month.png","numbers.png","shapes.png","solarsystem.png","sports.png",
        // "vegetables.png","vehicles.png","vitamin.png","mathsign.png"]
        dataList.clear()
        data.forEach { mData ->
            dataList.add(GridViewModal(image = mData))
        }

        return dataList
    }
}