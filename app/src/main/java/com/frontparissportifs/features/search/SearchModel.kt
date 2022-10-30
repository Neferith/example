package com.frontparissportifs.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frontparissportifs.model.Team
import com.frontparissportifs.repository.TeamRepository
import com.frontparissportifs.utils.DataState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchModel @Inject constructor(
    private val teamRepository: TeamRepository
) : ISearchContract.Model, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    private val _dataState: MutableLiveData<DataState<List<Team>>> = MutableLiveData()
    override val dataState: LiveData<DataState<List<Team>>>
        get() = _dataState


    override fun searchTeams(
        leagueName: String?
    ) {
        launch {
                    teamRepository.getByLeagues(leagueName).onEach {
                            dataState -> _dataState.value = dataState
                    }
                        .launchIn(MainScope())
        }
    }
}

