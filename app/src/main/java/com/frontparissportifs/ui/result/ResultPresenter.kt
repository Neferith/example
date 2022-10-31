package com.frontparissportifs.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frontparissportifs.model.Team
import com.frontparissportifs.utils.DataState
import javax.inject.Inject

class ResultPresenter  @Inject constructor(
    val model: IResultContract.Model
    ) : IResultContract.Presenter, IResultContract.Model.OnFinishedListener {

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

    override fun onUserSelectKeyword(keyword:String) {

        model.searchTeams(keyword, this)
    }

    override fun onClickItem(position: Int, team: Team) {
        TODO("Not yet implemented")
    }

    override fun onFinished(string: DataState<List<Team>>) {
        _dataState.value = string
    }

}