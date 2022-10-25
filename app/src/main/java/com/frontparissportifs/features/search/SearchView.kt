package com.frontparissportifs.features.search

import com.frontparissportifs.model.Team

class SearchView:ISearchContract.View {
    override fun getSearchValue(): String {
        TODO("Not yet implemented")
    }
    override fun success(teams: List<Team>) {
        TODO("Not yet implemented")
    }
    override fun onFailure(codeError: String, message: String) {
        TODO("Not yet implemented")
    }
}