package com.frontparissportifs.ui.result

import androidx.lifecycle.LiveData
import com.frontparissportifs.model.Team
import com.frontparissportifs.ui.base.BaseContract
import com.frontparissportifs.utils.DataState

open interface ResultContract {

    interface View : BaseContract.View {
        fun goToDetail(team: Team)
    }

    open interface Model : BaseContract.Model {
        fun searchTeams(leagueName: String?, onResultListener: OnResultListener)
        interface OnResultListener {
            fun onReceivedResult(string: DataState<List<Team>>)
        }
    }

    interface Presenter : BaseContract.Presenter<View>, TeamAdapter.TeamItemListener {
        fun onUserSelectKeyword(keyword: String)
        val dataState: LiveData<DataState<List<Team>>>
    }

}