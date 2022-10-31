package com.frontparissportifs.ui.result

import androidx.lifecycle.LiveData
import com.frontparissportifs.features.search.IBaseContract
import com.frontparissportifs.model.Team
import com.frontparissportifs.utils.DataState

interface IResultContract {

    interface View: IBaseContract.View

    interface Model: IBaseContract.Model {
        fun searchTeams(leagueName:String?,onFinishedListener:OnFinishedListener)
        interface OnFinishedListener {
            fun onFinished(string: DataState<List<Team>>)
        }
    }

    interface Presenter: IBaseContract.Presenter<View>{
        fun onUserSelectKeyword(keyword:String)
        val dataState: LiveData<DataState<List<Team>>>
    }

}