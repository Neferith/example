package com.frontparissportifs.features.search

import androidx.annotation.UiThread

interface IBaseContract {

    @UiThread
    interface View

    interface Presenter<V : View> {
        fun attach(view: V)
        fun detach()
    }

}