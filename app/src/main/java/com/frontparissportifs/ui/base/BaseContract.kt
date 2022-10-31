package com.frontparissportifs.features.search

import androidx.annotation.UiThread

//@TODO : A remplacer
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