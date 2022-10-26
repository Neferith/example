package com.frontparissportifs.features.search

import com.frontparissportifs.model.Team
import com.frontparissportifs.network.ApiClient
import com.frontparissportifs.network.TeamMapper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SearchModel : ISearchContract.Model, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun searchTeams(
        leagueName: String?,
        onFinishedListener: ISearchContract.Model.OnFinishedListener<List<Team>>
    ) {

        launch {
            try {
                if (leagueName == null) {
                    throw ParameterException("League name is null")
                }
                val response = ApiClient.apiService.getByLeagues(leagueName)
                withContext(Dispatchers.Main) {
                    val res: List<Team> = TeamMapper().mapFromEntityList(response.teams)
                    onFinishedListener.success(res)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}