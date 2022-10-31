package com.frontparissportifs.ui.result

import androidx.lifecycle.LiveData
import com.frontparissportifs.TeamAdapter
import com.frontparissportifs.features.search.IBaseContract
import com.frontparissportifs.model.Team
import com.frontparissportifs.utils.DataState

interface IResultContract {

    interface View: IBaseContract.View {
        fun goToDetail(team: Team)
    }

    interface Model: IBaseContract.Model {
        fun searchTeams(leagueName: String?, onResultListener: OnResultListener)
        interface OnResultListener {
            fun onReceivedResult(string: DataState<List<Team>>)
        }
    }

    interface Presenter : IBaseContract.Presenter<View>, TeamAdapter.TeamItemListener {
        fun onUserSelectKeyword(keyword: String)
        val dataState: LiveData<DataState<List<Team>>>
    }

}