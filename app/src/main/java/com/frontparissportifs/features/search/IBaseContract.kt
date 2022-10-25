package com.frontparissportifs.features.search

import androidx.annotation.UiThread

interface IBaseContract {

    @UiThread
    interface View

    interface Presenter<V : View, M : Model> {
        fun attach(view: V, model: M)
        fun detach()
    }

    interface Model

}