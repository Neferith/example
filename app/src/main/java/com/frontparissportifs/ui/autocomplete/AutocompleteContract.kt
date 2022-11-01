package com.frontparissportifs.ui.autocomplete

import androidx.lifecycle.LiveData
import com.frontparissportifs.ui.base.BaseContract
import com.frontparissportifs.utils.DataState

interface AutocompleteContract : BaseContract {

    interface View : BaseContract.View {
        fun updateCurrentKeywordSelected(keyword: String)
    }

    interface Model : BaseContract.Model {
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