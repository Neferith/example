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

        // nested interface to be
        interface OnFinishedListener {
            // function to be called
            // once the Handler of Model class
            // completes its execution
            fun onFinished(string: DataState<List<String>>)
        }


    }

    interface Presenter: IBaseContract.Presenter<View, Model>{

        val dataState: LiveData<DataState<List<String>>>

        fun onChooseItemInAutocompleteList(item:String)
    }

}