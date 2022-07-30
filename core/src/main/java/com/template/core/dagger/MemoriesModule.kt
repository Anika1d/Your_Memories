package com.template.core.dagger

import com.template.core.room.AppRoom
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class MemoriesModule {
    @Singleton
    @Provides
    fun getMemoriesDao(appDb: AppRoom) = appDb.getMemoriesDao()
}