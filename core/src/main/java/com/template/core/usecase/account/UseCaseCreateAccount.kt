package com.template.core.usecase.account

import com.template.core.model.account.EnterAccount
import com.template.core.room.repository.impl.AccountRepositoryImpl
import javax.inject.Inject

class UseCaseCreateAccount @Inject constructor(val repository: AccountRepositoryImpl) {
    suspend operator fun invoke(enterAccount: EnterAccount) = repository.createAccount(
        enterAccount = enterAccount
    )
}