package com.frontparissportifs.ui.detail

import androidx.lifecycle.LiveData
import com.frontparissportifs.features.search.IBaseContract
import com.frontparissportifs.model.Team

interface IDetailContract {

    interface View: IBaseContract.View

    interface Model : IBaseContract.Model

    interface Presenter: IBaseContract.Presenter<View> {
        fun selectTeam(team: Team)
        val dataState: LiveData<Team>
    }

}