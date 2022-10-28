package com.frontparissportifs.repository

import com.frontparissportifs.features.search.ParameterException
import com.frontparissportifs.model.Team
import com.frontparissportifs.network.TeamApi
import com.frontparissportifs.network.TeamMapper
import com.frontparissportifs.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TeamRepository @Inject
constructor(private val teamApi: TeamApi, private val teamMapper: TeamMapper){

    suspend fun getByLeagues(leagueName:String?): Flow<DataState<List<Team>>> = flow {
        emit(DataState.Loading)
        try {
            if (leagueName == null) {
                throw ParameterException("League name is null")
            }
            val networkBlogs = teamApi.getByLeagues(leagueName)
            val teams = teamMapper.mapFromEntityList(networkBlogs.teams)

            emit(DataState.Success(teams))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}