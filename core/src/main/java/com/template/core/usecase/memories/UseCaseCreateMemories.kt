package com.template.core.usecase.memories

import com.template.core.model.memories.CoreMemories
import com.template.core.model.memories.EnterMemories
import com.template.core.room.repository.impl.MemoriesRepositoryImpl
import javax.inject.Inject

class UseCaseCreateMemories @Inject constructor(val repository: MemoriesRepositoryImpl) {
    suspend operator fun invoke(enterMemories: EnterMemories) =
        repository.createMemories(enterMemories = enterMemories)
}