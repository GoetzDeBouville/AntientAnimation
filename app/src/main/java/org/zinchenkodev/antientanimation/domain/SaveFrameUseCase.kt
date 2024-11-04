package org.zinchenkodev.antientanimation.domain

import org.zinchenkodev.antientanimation.domain.api.AnimationRepository
import org.zinchenkodev.antientanimation.domain.models.Line

class SaveFrameUseCase(private val repository: AnimationRepository) {

    suspend operator fun invoke(animationSetId: Long = 0L, frame: List<Line>) {
        repository.saveFrame(animationSetId, frame)
    }

}