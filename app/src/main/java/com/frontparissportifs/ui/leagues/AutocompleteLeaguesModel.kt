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

    private val _dataState: MutableLiveData<DataState<List<League>>> = MutableLiveData()
    override val dataState: LiveData<DataState<List<League>>>
        get() = _dataState


    override fun autocompleteLeagues(
        keyword: String?
    ) {
        launch {
            leagueRepository.autocompleteKeywords(keyword).onEach { dataState -> _dataState.value = dataState  }
                .launchIn(MainScope())
        }
    }
}
