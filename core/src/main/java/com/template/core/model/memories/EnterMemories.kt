package com.template.core.model.memories

import androidx.room.ColumnInfo

data class EnterMemories(
    val idOwner: Long,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val images: ByteArray,
    val name: String,
    val description: String,
    //val idMarkedFriends: List<Long>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EnterMemories

        if (idOwner != other.idOwner) return false
        if (!images.contentEquals(other.images)) return false
        if (name != other.name) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = idOwner.hashCode()
        result = 31 * result + images.contentHashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}
