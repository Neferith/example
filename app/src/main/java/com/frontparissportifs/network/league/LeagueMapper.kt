package com.frontparissportifs.network.league

import com.frontparissportifs.model.League
import com.frontparissportifs.utils.EntityMapper
import javax.inject.Inject

class LeagueMapper @Inject
constructor() : EntityMapper<LeagueObjectResponse, League> {
    override fun mapFromEntity(entity: LeagueObjectResponse): League {
        return League(
            id = entity.idLeague,
            name = entity.strLeague,
            sport = entity.strSport,
            alternateName = entity.strLeagueAlternate
        )
    }

    override fun mapToEntity(domainModel: League): LeagueObjectResponse {
        return LeagueObjectResponse(
            idLeague = domainModel.id,
            strLeague = domainModel.name,
            strSport = domainModel.sport,
            strLeagueAlternate = domainModel.alternateName
        )
    }

    fun mapFromEntityList(entities: List<LeagueObjectResponse>): List<League> {
        return entities.map { mapFromEntity(it) }
    }

}