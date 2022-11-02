package com.frontparissportifs.ui.autocomplete

import com.frontparissportifs.repository.LeagueRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AutocompleteModel @Inject constructor(
    private val leagueRepository: LeagueRepository
) : AutocompleteContract.Model, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun allSoccerLeagues(
        onFinishedListener: AutocompleteContract.Model.OnFinishedListener<List<String>>
    ) {
        launch {
            leagueRepository.foundAllSoccerLeagues().onEach { dataState ->
                println(dataState)
                onFinishedListener.onFinished(dataState)
            }.launchIn(MainScope())
        }
    }

    override fun fetchLeagueExist(leagueName:String,
        onFinishedListener: AutocompleteContract.Model.OnFinishedListener<Boolean>
    ) {
        launch {
            leagueRepository.fetchIsLeagueExist(leagueName).onEach { dataState ->
                println(dataState)
                onFinishedListener.onFinished(dataState)
            }.launchIn(MainScope())
        }
    }

}
