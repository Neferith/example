package com.frontparissportifs.ui.autocomplete

import androidx.lifecycle.LiveData
import com.frontparissportifs.features.search.IBaseContract
import com.frontparissportifs.utils.DataState

interface IAutocompleteLeaguesContract : IBaseContract {

    interface View: IBaseContract.View  {
        fun updateCurrentKeywordSelected(keyword:String)
    }

    interface Model: IBaseContract.Model {
        fun autocompleteLeagues(keyword:String?)
        fun allSoccerLeagues(onFinishedListener:OnFinishedListener)
        interface OnFinishedListener {
            fun onFinished(string: DataState<List<String>>)
        }
    }

    interface Presenter: IBaseContract.Presenter<View>{
        val dataState: LiveData<DataState<List<String>>>
        fun onChooseItemInAutocompleteList(item:String)
    }

}