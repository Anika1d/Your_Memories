package com.template.core.usecase.memories


import com.template.core.room.repository.impl.MemoriesRepositoryImpl
import javax.inject.Inject

class UseCaseGetMemoriesById @Inject constructor(val repository: MemoriesRepositoryImpl) {
    suspend operator fun invoke(idMemories: Long) =
        repository.getMemoriesById(idMemories)

}