package com.template.core.room.entities

import android.provider.ContactsContract
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.template.core.model.account.CoreAccount
import com.template.core.model.account.EnterAccount

@Entity(
    tableName = "accounts"
)
class AccountsDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val email: String,
    val password: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val avatar: ByteArray,
    @ColumnInfo(name = "created_at") val createdAt: Long

) {
    fun toAccount() = CoreAccount(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password,
        avatar = avatar
    )

    companion object {
        fun enterAccount(enterAccount: EnterAccount) = AccountsDbEntity(
            id = 0,
            firstName = enterAccount.firstName,
            lastName = enterAccount.lastName,
            email = enterAccount.email,
            password = enterAccount.password,
            createdAt = System.currentTimeMillis(),
            avatar = enterAccount.avatar
        )
    }
}