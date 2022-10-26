package com.frontparissportifs.features.search

import androidx.annotation.UiThread

//@TODO : A remplacer
class ParameterException(message: String?) : Exception(message)
class RequestException(message: String?) : Exception(message)

interface IBaseContract {

    @UiThread
    interface View

    interface Presenter<V : View, M : Model> {
        fun attach(view: V, model: M)
        fun detach()
    }

    interface Model

}