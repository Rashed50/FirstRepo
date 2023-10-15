package com.shamim.kidsedu.view.adapter

import com.shamim.kidsedu.model.PlayModel

interface OnItemEventClickListener {

    fun home()
    fun autoPlay(playModel: PlayModel, position: Int)
    fun music(isOn: Boolean)
    fun preview()
    fun next()
}