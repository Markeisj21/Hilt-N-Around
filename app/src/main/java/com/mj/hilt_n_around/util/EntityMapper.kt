package com.mj.hilt_n_around.util

interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity:Entity):DomainModel
    fun mapToEntity(domainModel: DomainModel):Entity
}