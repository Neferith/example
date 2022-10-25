package com.frontparissportifs.features.search

import com.frontparissportifs.model.Team

interface ISearchContract:IBaseContract {

    interface View:IBaseContract.View,Model.OnSearchFinishedListener  {
        fun getSearchValue():String
    }

    interface Model:IBaseContract.Model {
        fun searchTeams(leagueName:String?)

        interface OnSearchFinishedListener {
            fun success(teams:List<Team>)
            fun onFailure(codeError:String , message:String)
        }
    }

    interface Presenter:IBaseContract.Presenter<View, Model>{

        fun onSearchClick()

    }
}