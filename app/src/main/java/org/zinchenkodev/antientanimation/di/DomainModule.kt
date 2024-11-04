package org.zinchenkodev.antientanimation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.zinchenkodev.antientanimation.domain.ClearDbAndResetTableUseCase
import org.zinchenkodev.antientanimation.domain.GetFrameListUseCase
import org.zinchenkodev.antientanimation.domain.GetFramesCountUseCase
import org.zinchenkodev.antientanimation.domain.SaveFrameUseCase
import org.zinchenkodev.antientanimation.domain.api.AnimationRepository

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideSaveFrameUseCase(repository: AnimationRepository) = SaveFrameUseCase(repository)

    @Provides
    fun provideGetFrameListUseCase(repository: AnimationRepository) =
        GetFrameListUseCase(repository)

    @Provides
    fun provideGetFramesCountUseCase(repository: AnimationRepository) =
        GetFramesCountUseCase(repository)

    @Provides
    fun provideClearDbAndResetTableUseCase(repository: AnimationRepository) =
        ClearDbAndResetTableUseCase(repository)

}