package com.template.core.model.memories

import androidx.room.ColumnInfo

data class ChangedMemories(
    val id:Long,
    @ColumnInfo(name = "id_owner") val idOwner: Long,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val images: ByteArray,
    val name: String,
    val description: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChangedMemories

        if (id != other.id) return false
        if (idOwner != other.idOwner) return false
        if (!images.contentEquals(other.images)) return false
        if (name != other.name) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + idOwner.hashCode()
        result = 31 * result + images.contentHashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}
