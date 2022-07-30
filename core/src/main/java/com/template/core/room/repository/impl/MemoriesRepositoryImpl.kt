package com.template.core.room.repository.impl

import com.template.core.model.memories.ChangedMemories
import com.template.core.model.memories.CoreMemories
import com.template.core.model.memories.EnterMemories
import com.template.core.room.dao.memories.MemoriesDao
import com.template.core.room.entities.MemoriesDbEntity
import com.template.core.room.repository.MemoriesRepository
import javax.inject.Inject

class MemoriesRepositoryImpl @Inject constructor(val memoriesDao: MemoriesDao) :
    MemoriesRepository {
    override suspend fun getMemoriesList(idOwner: Long): List<CoreMemories> =
        memoriesDao.getListMemories(id = idOwner).map {
            it.toMemories()
        }

    override suspend fun createMemories(enterMemories: EnterMemories) {
        memoriesDao.createMemories(
            memoriesDbEntity =
            MemoriesDbEntity.enterMemories(enterMemories)
        )
    }

    override suspend fun updateMemories(coreMemories: CoreMemories) {
        memoriesDao.updateMemories(
            ChangedMemories(
                id = coreMemories.id,
                name = coreMemories.name,
                description = coreMemories.description,
                idOwner = coreMemories.idOwner,
                images = coreMemories.images
            )
        )
    }

    override suspend fun getMemoriesById(id: Long): CoreMemories? =
        memoriesDao.getMemoriesById(id)?.toMemories()
}