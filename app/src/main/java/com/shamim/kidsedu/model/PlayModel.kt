package com.shamim.kidsedu.model

import com.shamim.kidsedu.R

data class PlayModel(val image: Int?= null, var audio: Int?= null) {
    private var dataList = ArrayList<PlayModel>()

    fun playDataList():List<PlayModel>{

        dataList.clear()
//        dataList.add(PlayModel( R.drawable.a, R.raw.a))
//        dataList.add(PlayModel( R.drawable.b, R.raw.b))
//        dataList.add(PlayModel( R.drawable.c, R.raw.c))


        return  dataList
    }
}
