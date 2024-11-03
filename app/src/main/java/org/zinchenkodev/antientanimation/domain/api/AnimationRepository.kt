package org.zinchenkodev.antientanimation.domain.api

import org.zinchenkodev.antientanimation.domain.models.Line

interface AnimationRepository {
    suspend fun saveFrame(animationSetId: Long = 0L, frame: List<Line>)

    suspend fun getFrameList(animationSetId: Long, startIndex: Long): List<List<Line>>
}