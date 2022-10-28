package com.frontparissportifs.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frontparissportifs.model.Team
import com.frontparissportifs.network.TeamApi
import com.frontparissportifs.network.TeamMapper
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

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        launch {
            when (mainStateEvent) {
                is MainStateEvent.GetTeamEvents -> {
                    teamRepository.getByLeagues("").onEach { dataState -> _dataState.value = dataState  }
                        .launchIn(this)
                }

                is MainStateEvent.None -> {
                    // No action
                }
            }
        }
    }

    override fun searchTeams(
        leagueName: String?,
        onFinishedListener: ISearchContract.Model.OnFinishedListener<List<Team>>
    ) {
        launch {

                    teamRepository.getByLeagues(leagueName).onEach { dataState -> _dataState.value = dataState  }
                        .launchIn(MainScope())



        }
    }

    /* private val job = Job()
     override val coroutineContext: CoroutineContext = job + Dispatchers.IO

     @Inject
     lateinit var teamMapper: TeamMapper

     override fun searchTeams(
         leagueName: String?,
         onFinishedListener: ISearchContract.Model.OnFinishedListener<List<Team>>
     ) {
         launch {
             try {
                 if (leagueName == null) {
                     throw ParameterException("League name is null")
                 }
                 val response = teamRepository.getByLeagues(leagueName)//ApiClient.apiService.getByLeagues(leagueName)
                 withContext(Dispatchers.Main) {

                     onFinishedListener.success(response)
                 }
             } catch (e: Exception) {
                 e.printStackTrace()
             }
         }
     }*/

}

sealed class MainStateEvent {
    object GetTeamEvents : MainStateEvent()
    object None : MainStateEvent()
}
