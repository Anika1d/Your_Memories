package com.template.core.usecase.memories

import com.template.core.model.memories.CoreMemories
import com.template.core.room.repository.impl.MemoriesRepositoryImpl
import javax.inject.Inject

class UseCaseUpdateMemories  @Inject constructor(val repository: MemoriesRepositoryImpl) {
    suspend operator fun invoke(coreMemories: CoreMemories) =
        repository.updateMemories(coreMemories = coreMemories)
}