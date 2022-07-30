package com.template.core.usecase.account

import com.template.core.room.repository.AccountRepository
import com.template.core.room.repository.impl.AccountRepositoryImpl
import javax.inject.Inject

class UseCaseAuth @Inject constructor(private val repository: AccountRepositoryImpl) {
    suspend operator fun invoke(email: String, password: String) =
        repository.auth(email = email, password = password)
}