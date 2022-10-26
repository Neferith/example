package com.frontparissportifs.features.search

import android.util.Log
import com.frontparissportifs.model.Team
import javax.inject.Inject

open class SearchPresenter @Inject constructor() : ISearchContract.Presenter,
    ISearchContract.Model.OnFinishedListener<List<Team>> {

    var view: ISearchContract.View? = null
    var model: ISearchContract.Model? = null

    override fun attach(view: ISearchContract.View, model: ISearchContract.Model) {
        this.view = view
        this.model = model
    }

    override fun detach() {
        this.view = null
        this.model = null
    }

    override fun onSearchClick() {
        val searchValue = view?.getSearchValue()
        model?.searchTeams(searchValue, this)
    }


    override fun success(result:List<Team>) {
        Log.d("test","test")
    }

    override fun onFailure(codeError:String , message:String) {}

}