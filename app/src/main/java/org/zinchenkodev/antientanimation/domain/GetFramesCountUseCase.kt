package org.zinchenkodev.antientanimation.domain

import org.zinchenkodev.antientanimation.domain.api.AnimationRepository

class GetFramesCountUseCase(private val repository: AnimationRepository) {

    suspend operator fun invoke(animationSetId: Long = 0): Long {
        return repository.getFramesCount(animationSetId)
    }

}