package com.frontparissportifs.dao

import com.frontparissportifs.model.League
import com.frontparissportifs.utils.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<LeagueCacheEntity, League> {

    override fun mapFromEntity(entity: LeagueCacheEntity): League {
        return League(
            id = entity.id,
            name = entity.name,
            sport = entity.sport,
            alternateName = entity.alternateName
        )
    }

    override fun mapToEntity(domainModel: League): LeagueCacheEntity {
        return LeagueCacheEntity(
            id = domainModel.id,
            name = domainModel.name,
            sport = domainModel.sport,
            alternateName = domainModel.alternateName
        )
    }

    fun mapFromEntityListToListString(entities: List<LeagueCacheEntity>): List<String> {
        return entities.map { it.name }
    }

}