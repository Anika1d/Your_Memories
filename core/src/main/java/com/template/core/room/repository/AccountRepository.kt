package com.template.core.room.repository

import com.template.core.model.account.CoreAccount
import com.template.core.model.account.EnterAccount

interface AccountRepository {
    suspend fun auth(email: String, password: String): CoreAccount?
    suspend fun getAccountByEmail(email: String):CoreAccount?
    suspend fun createAccount(enterAccount: EnterAccount)
}