package com.shamim.kidsedu.model

import com.shamim.kidsedu.R

data class PlayModel(val image: Int?= null, val audio: Int?= null) {
    private var dataList = ArrayList<PlayModel>()

    fun playDataList():List<PlayModel>{

        val alfabetaImage = listOf<Int>(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
        )
        val audio = listOf<Int>(
            R.raw.a,
            R.raw.b,
            R.raw.c,

        )
        dataList.clear()
        alfabetaImage.forEach { image->
          audio.forEach { audio->
              dataList.addAll(listOf(PlayModel(image,audio)))
          }
        }
        return  dataList
    }
}
