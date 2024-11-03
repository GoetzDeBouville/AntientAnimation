package org.zinchenkodev.antientanimation.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.zinchenkodev.antientanimation.data.AppDatabase
import org.zinchenkodev.antientanimation.data.dao.AnimationDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
            .addMigrations()
            .build()


    @Provides
    fun provideAnimationDao(database: AppDatabase): AnimationDao {
        return database.animationDao()
    }
}