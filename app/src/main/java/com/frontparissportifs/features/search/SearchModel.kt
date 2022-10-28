package com.frontparissportifs.features.search

import com.frontparissportifs.model.Team
import com.frontparissportifs.network.TeamApi
import com.frontparissportifs.network.TeamMapper
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchModel @Inject constructor(
    private val teamApi: TeamApi
) : ISearchContract.Model, CoroutineScope {

    private val job = Job()
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
                val response = teamApi.getByLeagues(leagueName)//ApiClient.apiService.getByLeagues(leagueName)
                withContext(Dispatchers.Main) {
                    val res: List<Team> = teamMapper.mapFromEntityList(response.teams)
                    onFinishedListener.success(res)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}