package com.frontparissportifs.ui.detail

import androidx.lifecycle.LiveData
import com.frontparissportifs.model.Team
import com.frontparissportifs.ui.base.BaseContract

interface DetailContract {

    interface View : BaseContract.View

    interface Model : BaseContract.Model

    interface Presenter : BaseContract.Presenter<View> {
        fun selectTeam(team: Team)
        val dataState: LiveData<Team>
    }

}