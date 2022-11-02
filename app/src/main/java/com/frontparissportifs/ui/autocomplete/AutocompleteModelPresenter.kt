package com.frontparissportifs.ui.autocomplete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frontparissportifs.utils.DataState
import javax.inject.Inject

open class AutocompleteModelPresenter @Inject constructor(
    val model: AutocompleteContract.Model
) : AutocompleteContract.Presenter,
    AutocompleteContract.Model.OnFinishedListener<List<String>>{

    var view: AutocompleteContract.View? = null

    private val _allLeaguesNamesState: MutableLiveData<DataState<List<String>>> = MutableLiveData()
    override val allLeaguesNamesState: LiveData<DataState<List<String>>>
        get() = _allLeaguesNamesState

    override fun onChooseItemInAutocompleteList(item: String) {
        view?.updateCurrentKeywordSelected(item)
    }

    //@TODO : Gérer une erreur quand le mot clef ne correspond pas à une league de foot existant et vider la liste des résultats
    override fun performSearch(keyword: String) {
        view?.executeCloseKeyboard()
        model.fetchLeagueExist(keyword, object :
            AutocompleteContract.Model.OnFinishedListener<Boolean> {
            override fun onFinished(result: DataState<Boolean>) {
                when (result) {
                    is DataState.Success<Boolean> -> {
                        if(result.data) {
                            view?.updateCurrentKeywordSelected(keyword)
                        }
                    }
                    is DataState.Loading -> {}
                    is DataState.Error -> {}
                }
            }
        })
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

    override fun onFinished(result: DataState<List<String>>) {
        _allLeaguesNamesState.value = result
    }

}