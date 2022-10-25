package com.frontparissportifs.features.search

open class SearchPresenter:ISearchContract.Presenter {

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
        model?.searchTeams(searchValue)
    }

}