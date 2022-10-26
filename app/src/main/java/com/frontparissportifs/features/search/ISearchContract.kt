package com.frontparissportifs.features.search

import com.frontparissportifs.model.Team

interface ISearchContract:IBaseContract {

    interface View:IBaseContract.View  {
        fun getSearchValue():String
    }

    interface Model:IBaseContract.Model {
        fun searchTeams(leagueName:String?, onFinishedListener: OnFinishedListener<List<Team>>)
        interface OnFinishedListener<T> {
            fun success(result:T)
            fun onFailure(codeError:String , message:String)
        }
    }

    interface Presenter:IBaseContract.Presenter<View, Model>{
        fun onSearchClick()
    }

}