package com.iamsdt.communicatebetweenviewandvm.ui

import android.view.View

enum class Status{
    LOGIN,
    SIGNUP,
    FAILED
}

fun View.gone(){
    visibility = View.GONE
}

fun View.vis(){
    visibility = View.VISIBLE
}