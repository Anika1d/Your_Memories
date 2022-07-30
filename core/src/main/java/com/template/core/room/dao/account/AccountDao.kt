package com.template.core.room.dao.account

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.template.core.model.account.EnterAccount
import com.template.core.room.entities.AccountsDbEntity

@Dao
interface AccountDao {

    @Query("SELECT * FROM  accounts WHERE email=:email AND password=:password")
    suspend fun auth(email: String, password: String): AccountsDbEntity?


    @Query("SELECT * FROM  accounts WHERE email=:email")
    suspend fun getAccountByEmail(email: String): AccountsDbEntity?

    @Insert(entity = AccountsDbEntity::class)
    suspend fun createAccount(accountDbEntity: AccountsDbEntity)
}