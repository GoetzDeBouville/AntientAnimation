package org.zinchenkodev.antientanimation.domain

import org.zinchenkodev.antientanimation.domain.api.AnimationRepository

class ClearDbAndResetTableUseCase(private val repository: AnimationRepository) {

    suspend operator fun invoke() {
        repository.clearDbAndResetTable()
    }

}