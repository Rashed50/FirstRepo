package com.shamim.kidsedu.model

import com.shamim.kidsedu.R

data class GridViewModal(
    val title: String? = null,
    val image: Int? = null
){
    private var dataList = ArrayList<GridViewModal>()


    fun getDataList():List<GridViewModal>{


        dataList.add(GridViewModal("Letter", R.drawable.image))
        dataList.add(GridViewModal("Design", R.drawable.image))
        dataList.add(GridViewModal("Editing ", R.drawable.image))
        dataList.add(GridViewModal("Download", R.drawable.image))
        dataList.add(GridViewModal("Alphabet ", R.drawable.image))
        dataList.add(GridViewModal("Background", R.drawable.image))
        dataList.add(GridViewModal("Letter", R.drawable.image))
        dataList.add(GridViewModal("Download", R.drawable.image))

        return dataList
    }
}