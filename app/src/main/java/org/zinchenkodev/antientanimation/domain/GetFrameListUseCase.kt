package org.zinchenkodev.antientanimation.domain

import org.zinchenkodev.antientanimation.domain.api.AnimationRepository
import org.zinchenkodev.antientanimation.domain.models.Line

class GetFrameListUseCase(private val repository: AnimationRepository) {

    suspend operator fun invoke(animationSetId: Long = 0L, startIndex: Long): List<List<Line>> {
        return repository.getFrameList(animationSetId, startIndex)
    }

}