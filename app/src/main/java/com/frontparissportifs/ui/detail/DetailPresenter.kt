package com.frontparissportifs.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frontparissportifs.model.Team
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    val model: DetailContract.Model
) : DetailContract.Presenter {

    var view: DetailContract.View? = null

    private val _dataState: MutableLiveData<Team> = MutableLiveData()
    override val dataState: LiveData<Team>
        get() = _dataState

    override fun selectTeam(team: Team) {
        _dataState.value = team
    }

    override fun attach(view: DetailContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

}