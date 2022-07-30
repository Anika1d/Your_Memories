package com.template.core.room.dao.memories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.template.core.model.memories.ChangedMemories
import com.template.core.model.memories.CoreMemories
import com.template.core.model.memories.EnterMemories
import com.template.core.room.entities.MemoriesDbEntity

@Dao
interface MemoriesDao {

    @Query("SELECT * FROM memories WHERE id_owner=:id")
    suspend fun getListMemories(id: Long): List<MemoriesDbEntity>


    @Query("SELECT * FROM memories WHERE id=:id")
    suspend fun getMemoriesById(id: Long):MemoriesDbEntity?

    @Insert(entity = MemoriesDbEntity::class)
    suspend fun createMemories(memoriesDbEntity: MemoriesDbEntity)

    @Update(entity = MemoriesDbEntity::class)
    suspend fun updateMemories(changedMemories: ChangedMemories)
}

