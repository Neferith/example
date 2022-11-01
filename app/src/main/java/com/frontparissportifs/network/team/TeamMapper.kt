package com.frontparissportifs.network.team

import com.frontparissportifs.model.League
import com.frontparissportifs.model.Team
import com.frontparissportifs.utils.EntityMapper
import javax.inject.Inject

class TeamMapper
@Inject
constructor() : EntityMapper<TeamObjectResponse, Team> {

    override fun mapFromEntity(entity: TeamObjectResponse): Team {
        return Team(
            id = entity.idTeam,
            name = entity.strTeam,
            description = entity.strDescriptionFR,
            badge = entity.strTeamBadge,
            leagues = extractLeagues(entity),
            country = entity.strCountry
        )
    }

    fun mapFromEntityList(entities: List<TeamObjectResponse>): List<Team> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapToEntity(domainModel: Team): TeamObjectResponse {
        TODO("Not yet implemented")
    }

    private fun extractLeagues(entity: TeamObjectResponse): List<League> {
        val leagues: ArrayList<League> = ArrayList()
        if (entity.idLeague != null && entity.strLeague != null) {

            leagues.add(League(entity.idLeague, entity.strLeague))
        }
        if (entity.idLeague2 != null && entity.strLeague2 != null) {

            leagues.add(League(entity.idLeague2, entity.strLeague2))
        }
        if (entity.idLeague3 != null && entity.strLeague3 != null) {

            leagues.add(League(entity.idLeague3,entity.strLeague3))
        }
        if(entity.idLeague4 != null && entity.strLeague4 != null) {

            leagues.add(League(entity.idLeague4,entity.strLeague4))
        }
        if(entity.idLeague5 != null && entity.strLeague5 != null) {

            leagues.add(League(entity.idLeague5,entity.strLeague5))
        }
        if(entity.idLeague6 != null && entity.strLeague6 != null) {

            leagues.add(League(entity.idLeague6,entity.strLeague6))
        }
        if(entity.idLeague7 != null && entity.strLeague7 != null) {

            leagues.add(League(entity.idLeague7,entity.strLeague7))
        }
        return leagues
    }

}
