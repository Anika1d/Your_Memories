package com.template.core.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.template.core.room.dao.account.AccountDao
import com.template.core.room.dao.memories.MemoriesDao
import com.template.core.room.entities.AccountsDbEntity
import com.template.core.room.entities.MemoriesDbEntity


@Database(
    version = 1,
    entities = [AccountsDbEntity::class, MemoriesDbEntity::class]

)
abstract class AppRoom : RoomDatabase() {
    abstract fun getAccountDao(): AccountDao
    abstract fun getMemoriesDao(): MemoriesDao

    companion object {
        private var dbInstance: AppRoom? = null
        fun getAppRoomInstance(context: Context): AppRoom {
            if (dbInstance == null) {
                dbInstance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppRoom::class.java,
                        "database2.db"
                    )
                        .allowMainThreadQueries()
                        .build()
            }
            return dbInstance as AppRoom
        }
    }
}

