package com.template.core.usecase.account


import com.template.core.room.repository.impl.AccountRepositoryImpl
import javax.inject.Inject

class UseCaseGetAccountByEmail @Inject constructor(val repository: AccountRepositoryImpl) {
    suspend operator fun invoke(email: String) = repository.getAccountByEmail(email = email)
}