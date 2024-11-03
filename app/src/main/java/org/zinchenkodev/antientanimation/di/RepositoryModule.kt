package org.zinchenkodev.antientanimation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.zinchenkodev.antientanimation.data.AppDatabase
import org.zinchenkodev.antientanimation.data.impl.AnimationRepositoryImpl
import org.zinchenkodev.antientanimation.domain.api.AnimationRepository

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideAnimationRepository(database: AppDatabase): AnimationRepository {
        return AnimationRepositoryImpl(database.animationDao())
    }
}