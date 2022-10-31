package com.frontparissportifs.ui.autocomplete

import com.frontparissportifs.repository.LeagueRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AutocompleteLeaguesModel @Inject constructor(
    private val leagueRepository: LeagueRepository
) : IAutocompleteLeaguesContract.Model, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO




    override fun autocompleteLeagues(
        keyword: String?
    ) {
       // TODO : Not Implemented yet
    }

    override fun allSoccerLeagues(onFinishedListener: IAutocompleteLeaguesContract.Model.OnFinishedListener) {
        launch {
            leagueRepository.foundAllSoccerLeagues().onEach { dataState ->
                println(dataState)


                onFinishedListener.onFinished(dataState)
            }.launchIn(MainScope())
        }
    }
}