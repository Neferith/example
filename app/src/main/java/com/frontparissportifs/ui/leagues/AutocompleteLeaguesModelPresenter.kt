package com.frontparissportifs.ui.leagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frontparissportifs.features.search.ISearchContract
import com.frontparissportifs.ui.base.BasePresenter
import com.frontparissportifs.utils.DataState
import javax.inject.Inject

open class AutocompleteLeaguesModelPresenter @Inject constructor() : IAutocompleteLeaguesContract.Presenter,
    IAutocompleteLeaguesContract.Model.OnFinishedListener {


    var view: IAutocompleteLeaguesContract.View? = null
    var model: IAutocompleteLeaguesContract.Model? = null

    private val _dataState: MutableLiveData<DataState<List<String>>> = MutableLiveData()
    override val dataState: LiveData<DataState<List<String>>>
        get() = _dataState



    override fun onEnterChar() {
        TODO("Not yet implemented")
    }

    override fun onChooseLeagues() {
        TODO("Not yet implemented")
    }

    override fun detach() {
        this.view = null
        this.model = null
    }

    override fun attach(
        view: IAutocompleteLeaguesContract.View,
        model: IAutocompleteLeaguesContract.Model
    ) {
        this.view = view
        this.model = model
        this.model?.allSoccerLeagues(this)
    }

    override fun onFinished(string: DataState<List<String>>) {
        _dataState.value = string
    }


}