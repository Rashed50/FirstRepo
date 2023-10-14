package com.shamim.kidsedu.view.adapter

import com.shamim.kidsedu.model.PlayModel

interface OnItemEventClickListener {

    fun home()
    fun autoPlay(playModel: PlayModel)
    fun music(isOn: Boolean)
}