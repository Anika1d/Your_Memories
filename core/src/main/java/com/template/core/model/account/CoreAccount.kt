package com.template.core.model.account

import androidx.room.ColumnInfo


data class CoreAccount (
    val id:Long,
    @ColumnInfo(name="first_name")    val firstName:String,
    @ColumnInfo(name="last_name")      val lastName:String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val avatar: ByteArray,
    val email:String,
    val password:String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CoreAccount

        if (id != other.id) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (!avatar.contentEquals(other.avatar)) return false
        if (email != other.email) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + avatar.contentHashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}