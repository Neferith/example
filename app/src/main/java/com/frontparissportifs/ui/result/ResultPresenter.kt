package com.frontparissportifs.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frontparissportifs.model.Team
import com.frontparissportifs.utils.DataState
import javax.inject.Inject

class ResultPresenter @Inject constructor(
    val model: IResultContract.Model
) : IResultContract.Presenter, IResultContract.Model.OnResultListener {

    var view: IResultContract.View? = null

    private val _dataState: MutableLiveData<DataState<List<Team>>> = MutableLiveData()
    override val dataState: LiveData<DataState<List<Team>>>
        get() = _dataState

    override fun attach(view: IResultContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun onClickedTeam(team: Team) {
        view?.goToDetail(team)
    }

    override fun onUserSelectKeyword(keyword: String) {
        model.searchTeams(keyword, this)
    }

    override fun onReceivedResult(string: DataState<List<Team>>) {
        _dataState.value = string
    }

}