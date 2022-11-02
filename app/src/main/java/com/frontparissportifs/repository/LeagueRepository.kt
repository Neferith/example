package com.frontparissportifs.repository

import com.frontparissportifs.dao.CacheMapper
import com.frontparissportifs.dao.LeagueDao
import com.frontparissportifs.network.league.LeagueApi
import com.frontparissportifs.network.league.LeagueMapper
import com.frontparissportifs.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LeagueRepository @Inject
constructor(
    private val leagueApi: LeagueApi,
    private val leagueMapper: LeagueMapper,
    private  val leagueDao:LeagueDao, private val
    cacheMapper: CacheMapper){

    private var apiDataIsLoaded = false

    private suspend fun initializeDatabaseIfNeed() {
        if(!apiDataIsLoaded) {
            val apiLeagues = leagueApi.getAllLeagues()
            val convertApiLeagues = leagueMapper.mapFromEntityList(apiLeagues.leagues)
            apiDataIsLoaded = true
            for (league in convertApiLeagues) {
                if(league.sport.equals("Soccer")) {
                    leagueDao.insert(cacheMapper.mapToEntity(league))
                }
            }
        }
    }

    suspend fun foundAllSoccerLeagues(): Flow<DataState<List<String>>> = flow {
        initializeDatabaseIfNeed()
        try {
            val cacheLeagues = leagueDao.getAll()
            val leagues = cacheMapper.mapFromEntityListToListString(cacheLeagues)
            emit(DataState.Success(leagues))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun fetchIsLeagueExist(leagueName:String): Flow<DataState<Boolean>> = flow {
        initializeDatabaseIfNeed()
        try {

            val leagues = leagueDao.getByKeywordEqual(leagueName)
            if(leagues.isNotEmpty()) {
                emit(DataState.Success(true))
            } else {
                emit(DataState.Success(false))
            }


        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }



}