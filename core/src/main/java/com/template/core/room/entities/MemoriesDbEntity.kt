package com.template.core.room.entities

import androidx.lifecycle.LifecycleOwner
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.template.core.model.memories.CoreMemories
import com.template.core.model.memories.EnterMemories


@Entity(
    tableName = "memories",
)

class MemoriesDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "id_owner") val idOwner: Long,
    val name: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val images: ByteArray,
    val description: String,
    @ColumnInfo(name = "created_at") val createdAt: Long
) {
    fun toMemories() =
        CoreMemories(
            id = id,
            idOwner = idOwner,
            images = images,
            name = name,
            description = description
        )

    companion object {
        fun enterMemories(enterMemories: EnterMemories) = MemoriesDbEntity(
            id = 0,
            idOwner = enterMemories.idOwner,
            images = enterMemories.images,
            name = enterMemories.name,
            description = enterMemories.description,
            createdAt = System.currentTimeMillis(),
        )
    }
}