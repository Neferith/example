package com.frontparissportifs.ui.result

import com.frontparissportifs.repository.TeamRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

open class ResultModel @Inject constructor(
    private val teamRepository: TeamRepository
) : ResultContract.Model, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun searchTeams(
        leagueName: String?, onResultListener: ResultContract.Model.OnResultListener
    ) {
        launch {
            teamRepository.getByLeagues(leagueName).onEach {
                onResultListener.onReceivedResult(it)
            }
                .launchIn(MainScope())
        }
    }
}