package com.frontparissportifs.ui.detail

import androidx.lifecycle.LiveData
import com.frontparissportifs.features.search.BaseContract
import com.frontparissportifs.model.Team

interface DetailContract {

    interface View : BaseContract.View

    interface Model : BaseContract.Model

    interface Presenter : BaseContract.Presenter<View> {
        fun selectTeam(team: Team)
        val dataState: LiveData<Team>
    }

}