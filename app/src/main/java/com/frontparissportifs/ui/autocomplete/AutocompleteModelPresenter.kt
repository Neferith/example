package com.frontparissportifs.ui.autocomplete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frontparissportifs.utils.DataState
import javax.inject.Inject

open class AutocompleteModelPresenter @Inject constructor(
    val model: AutocompleteContract.Model
) : AutocompleteContract.Presenter,
    AutocompleteContract.Model.OnFinishedListener {

    var view: AutocompleteContract.View? = null

    private val _dataState: MutableLiveData<DataState<List<String>>> = MutableLiveData()
    override val dataState: LiveData<DataState<List<String>>>
        get() = _dataState

    override fun onChooseItemInAutocompleteList(item: String) {
        view?.updateCurrentKeywordSelected(item)
    }

    override fun detach() {
        this.view = null
    }

    override fun attach(
        view: AutocompleteContract.View
    ) {
        this.view = view
        this.model.allSoccerLeagues(this)
    }

    override fun onFinished(string: DataState<List<String>>) {
        _dataState.value = string
    }

}