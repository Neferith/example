package com.frontparissportifs.features.search

interface ISearchContract {

    interface View {

        fun getSearchValue():String

    }

    interface Model {
        fun searchTeams(leagueName:String)
    }

    interface Presenter {

        fun onSearchClick()



    }
}