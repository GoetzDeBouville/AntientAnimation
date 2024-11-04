package org.zinchenkodev.antientanimation.domain.api

import org.zinchenkodev.antientanimation.domain.models.Line

interface AnimationRepository {
    suspend fun saveFrame(animationSetId: Long, frame: List<Line>)

    suspend fun getFrameList(animationSetId: Long, startIndex: Long): List<List<Line>>

    suspend fun getFramesCount(animationSetId: Long): Long

    suspend fun clearDbAndResetTable()
}