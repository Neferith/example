package com.frontparissportifs.repository

import com.frontparissportifs.model.Team
import com.frontparissportifs.network.team.TeamApi
import com.frontparissportifs.network.team.TeamMapper
import com.frontparissportifs.network.team.TeamObjectResponse
import com.frontparissportifs.network.team.TeamResponse
import com.frontparissportifs.utils.DataState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TeamRepositoryTest {

    companion object {
        private val TEAM_RESPONSE: TeamResponse = mockk()
        private val TEAM_API_RESULT: List<TeamObjectResponse> = mockk()
        private val TEAM_CONVERT_LIST: List<Team> = mockk()
    }

    private val api: TeamApi = mockk()
    private val mapper: TeamMapper = mockk()

    private val repository: TeamRepository = TeamRepository(api, mapper)

    @Before
    fun setUp() {
        every { TEAM_RESPONSE.teams } returns TEAM_API_RESULT
        coEvery { api.getByLeagues("French Ligue 1") } returns TEAM_RESPONSE
        coEvery { mapper.mapFromEntityList(TEAM_API_RESULT) } returns TEAM_CONVERT_LIST
    }

    @Test
    fun getByLeaguesTwoSentence() = runTest {
        val sentencesNumber = repository.getByLeagues("French Ligue 1").count()
        assertEquals(sentencesNumber, 2)
    }

    @Test
    fun getByLeaguesFirsSentenceLoading() = runTest {
        val dataState = repository.getByLeagues("French Ligue 1").first()
        assertEquals(dataState, DataState.Loading)
    }

    @Test
    fun getByLeaguesSecondSentenceResult() = runTest {
        val dataState = repository.getByLeagues("French Ligue 1").last()
        assertEquals(dataState, DataState.Success(TEAM_CONVERT_LIST))
    }

}