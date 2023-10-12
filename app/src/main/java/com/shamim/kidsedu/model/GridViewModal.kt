package com.shamim.kidsedu.model

import com.shamim.kidsedu.R

data class GridViewModal(
    val title: String? = null,
    val image: Int? = null
){
    var dataList = ArrayList<GridViewModal>()


    fun getDataList():List<GridViewModal>{


        dataList.add(GridViewModal("Testa", R.drawable.ic_launcher_background))
        dataList.add(GridViewModal("Testa", R.drawable.ic_launcher_background))
        dataList.add(GridViewModal("Testa", R.drawable.ic_launcher_background))
        dataList.add(GridViewModal("Testa", R.drawable.ic_launcher_background))
        dataList.add(GridViewModal("Testa", R.drawable.ic_launcher_background))
        dataList.add(GridViewModal("Testa", R.drawable.ic_launcher_background))
        dataList.add(GridViewModal("Testa", R.drawable.ic_launcher_background))
        dataList.add(GridViewModal("Testa", R.drawable.ic_launcher_background))

        return dataList
    }
}