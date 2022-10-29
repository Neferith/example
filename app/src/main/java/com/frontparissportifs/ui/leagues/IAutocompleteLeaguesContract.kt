package com.frontparissportifs.ui.leagues

import androidx.lifecycle.LiveData
import com.frontparissportifs.features.search.IBaseContract
import com.frontparissportifs.model.League
import com.frontparissportifs.model.Team
import com.frontparissportifs.utils.DataState

interface IAutocompleteLeaguesContract : IBaseContract {

    interface View: IBaseContract.View  {
        fun getSearchValue():String
    }

    interface Model: IBaseContract.Model {
        fun autocompleteLeagues(keyword:String?)

        val dataState: LiveData<DataState<List<League>>>
    }

    interface Presenter: IBaseContract.Presenter<View, Model>{
        fun onEnterChar()
        fun onChooseLeagues()
    }

}