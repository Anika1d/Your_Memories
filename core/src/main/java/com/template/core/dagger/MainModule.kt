package com.template.core.dagger

import android.app.Application
import android.content.Context
import com.template.core.room.AppRoom
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MainModule ( val application: Application) {
    @Singleton
    @Provides
    fun getRoomDbInstance(): AppRoom {
        return AppRoom.getAppRoomInstance(provideAppContext())
    }

    @Singleton
    @Provides
    fun provideAppContext(): Context = application.applicationContext
}