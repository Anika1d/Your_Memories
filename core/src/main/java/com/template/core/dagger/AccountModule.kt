package com.template.core.dagger

import com.template.core.room.AppRoom
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module

class AccountModule {

    @Singleton
    @Provides
    fun getAccountDao(appDb: AppRoom) = appDb.getAccountDao()
}