package com.frontparissportifs.ui.base

import androidx.annotation.UiThread

class ParameterException(message: String?) : Exception(message)

interface BaseContract {

    @UiThread
    interface View

    interface Presenter<V : View> {
        fun attach(view: V)
        fun detach()
    }

    interface Model

}