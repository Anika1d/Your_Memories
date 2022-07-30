package com.template.core.room.repository

import com.template.core.model.memories.CoreMemories
import com.template.core.model.memories.EnterMemories


interface MemoriesRepository {
    suspend fun getMemoriesList(idOwner: Long): List<CoreMemories>
    suspend fun createMemories(enterMemories: EnterMemories)
    suspend fun updateMemories(coreMemories: CoreMemories)
    suspend fun getMemoriesById(id: Long): CoreMemories?

}