package com.example.ideaplatformtesttask.di

import android.content.Context
import com.example.ideaplatformtesttask.data.room.ItemCardDao
import com.example.ideaplatformtesttask.data.room.ItemCardDatabase
import com.example.ideaplatformtesttask.data.room.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ItemCardDatabase {
        return ItemCardDatabase.getDatabaseClient(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(itemCardDatabase: ItemCardDatabase): ItemCardDao {
        return itemCardDatabase.itemCardDao()
    }

    @Provides
    @Singleton
    fun provideRoomRepository(itemCardDao: ItemCardDao): RoomRepository {
        return RoomRepository(itemCardDao)
    }


}

