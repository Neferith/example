package com.frontparissportifs.features.search

import androidx.lifecycle.LiveData
import com.frontparissportifs.model.Team
import com.frontparissportifs.utils.DataState

interface ISearchContract:IBaseContract {

    interface View:IBaseContract.View  {
        fun getSearchValue():String
    }

    interface Model:IBaseContract.Model {
        fun searchTeams(leagueName:String?)

        val dataState: LiveData<DataState<List<Team>>>
    }

    interface Presenter:IBaseContract.Presenter<View, Model>{
        fun onSearchClick()
    }

}