package com.frontparissportifs.ui.autocomplete

import androidx.lifecycle.LiveData
import com.frontparissportifs.features.search.BaseContract
import com.frontparissportifs.utils.DataState

interface AutocompleteLeaguesContract : BaseContract {

    interface View : BaseContract.View {
        fun updateCurrentKeywordSelected(keyword: String)
    }

    interface Model : BaseContract.Model {
        fun autocompleteLeagues(keyword: String?)
        fun allSoccerLeagues(onFinishedListener: OnFinishedListener)
        interface OnFinishedListener {
            fun onFinished(string: DataState<List<String>>)
        }
    }

    interface Presenter : BaseContract.Presenter<View> {
        val dataState: LiveData<DataState<List<String>>>
        fun onChooseItemInAutocompleteList(item: String)
    }

}