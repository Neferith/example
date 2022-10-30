package com.frontparissportifs.ui.leagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frontparissportifs.features.search.ISearchContract
import com.frontparissportifs.model.League
import com.frontparissportifs.model.Team
import com.frontparissportifs.repository.LeagueRepository
import com.frontparissportifs.repository.TeamRepository
import com.frontparissportifs.utils.DataState
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
