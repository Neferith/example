package com.frontparissportifs.ui.leagues

import androidx.lifecycle.LiveData
import com.frontparissportifs.features.search.IBaseContract
import com.frontparissportifs.utils.DataState

interface IAutocompleteLeaguesContract : IBaseContract {

    interface View: IBaseContract.View  {
        fun getSearchValue():String
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
        fun onEnterChar()
        fun onChooseLeagues()

        val dataState: LiveData<DataState<List<String>>>
    }

}