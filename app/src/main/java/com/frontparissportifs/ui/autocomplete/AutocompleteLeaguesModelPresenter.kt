package com.frontparissportifs.ui.autocomplete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frontparissportifs.utils.DataState
import javax.inject.Inject

open class AutocompleteLeaguesModelPresenter @Inject constructor(
    val model: AutocompleteLeaguesContract.Model
) : AutocompleteLeaguesContract.Presenter,
    AutocompleteLeaguesContract.Model.OnFinishedListener {

    var view: AutocompleteLeaguesContract.View? = null

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
        view: AutocompleteLeaguesContract.View
    ) {
        this.view = view
        this.model.allSoccerLeagues(this)
    }

    override fun onFinished(string: DataState<List<String>>) {
        _dataState.value = string
    }

}