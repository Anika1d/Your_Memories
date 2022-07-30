package com.template.core.usecase.memories

import com.template.core.room.repository.impl.MemoriesRepositoryImpl
import javax.inject.Inject

class UseCaseGetMemoriesByOwner @Inject constructor(private val repository: MemoriesRepositoryImpl) {
    suspend operator fun invoke(idOwner: Long) = repository.getMemoriesList(idOwner = idOwner)
}