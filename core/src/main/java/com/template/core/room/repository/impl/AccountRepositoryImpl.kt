package com.template.core.room.repository.impl

import com.template.core.model.account.CoreAccount
import com.template.core.model.account.EnterAccount
import com.template.core.room.dao.account.AccountDao
import com.template.core.room.entities.AccountsDbEntity
import com.template.core.room.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(val accountDao: AccountDao) : AccountRepository {
    override suspend fun auth(email: String, password: String): CoreAccount? =
        accountDao.auth(email = email, password = password)?.toAccount()

    override suspend fun getAccountByEmail(email: String): CoreAccount? =
        accountDao.getAccountByEmail(email = email)?.toAccount()


    override suspend fun createAccount(enterAccount: EnterAccount) {
        accountDao.createAccount(
            AccountsDbEntity.enterAccount(enterAccount = enterAccount)
        )
    }
}