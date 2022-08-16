package com.template.core.model.account

import androidx.room.ColumnInfo


data class EnterAccount(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val avatar: ByteArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EnterAccount

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (!avatar.contentEquals(other.avatar)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + avatar.contentHashCode()
        return result
    }
}
