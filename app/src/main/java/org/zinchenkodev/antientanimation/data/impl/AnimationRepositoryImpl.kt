package org.zinchenkodev.antientanimation.data.impl

import android.database.sqlite.SQLiteException
import android.util.Log
import org.zinchenkodev.antientanimation.data.dao.AnimationDao
import org.zinchenkodev.antientanimation.data.models.AnimationSetFrameCrossRef
import org.zinchenkodev.antientanimation.data.models.FrameEntity
import org.zinchenkodev.antientanimation.data.models.FrameLineCrossRef
import org.zinchenkodev.antientanimation.data.toDomain
import org.zinchenkodev.antientanimation.data.toEntity
import org.zinchenkodev.antientanimation.domain.api.AnimationRepository
import org.zinchenkodev.antientanimation.domain.models.Line
import javax.inject.Inject

class AnimationRepositoryImpl @Inject constructor(private val dao: AnimationDao) :
    AnimationRepository {
    override suspend fun saveFrame(animationSetId: Long, frame: List<Line>) {
        val frameEntity = FrameEntity()
        val frameId =
            try {
                dao.insertFrame(frameEntity)
            } catch (e: SQLiteException) {
                Log.e(
                    TAG,
                    "Error saving frame, error -> ${e.localizedMessage}"
                )
                0L
            }

        frame.forEach { line ->
            val lineEntity = line.toEntity()
            val lineId = try {
                dao.insertLine(lineEntity)
            } catch (e: SQLiteException) {
                Log.e(
                    TAG,
                    "Error saving line, error -> ${e.localizedMessage}"
                )
                0L
            }

            try {
                dao.insertFrameLineCrossRef(FrameLineCrossRef(frameId = frameId, lineId = lineId))
            } catch (e: SQLiteException) {
                Log.e(
                    TAG,
                    "Error saving frame line cross ref, error -> ${e.localizedMessage}"
                )
            }
        }

        try {
            dao.insertAnimationSetFrameCrossRef(
                AnimationSetFrameCrossRef(
                    animationSetId = animationSetId,
                    frameId = frameId
                )
            )
        } catch (e: SQLiteException) {
            Log.e(TAG, "Error saving frame line cross ref, error -> ${e.localizedMessage}")
        }
    }

    override suspend fun getFrameList(animationSetId: Long, startIndex: Long): List<List<Line>> {
        val framesWithLines = try {
            dao.getFramesWithLines(animationSetId, startIndex, PAGGING_SIZE)
        } catch (e: SQLiteException) {
            Log.e(TAG, "Error getting frames with lines, error -> ${e.localizedMessage}")
            emptyList()
        }

        return framesWithLines.map {
            it.map { lineEntity ->
                lineEntity.toDomain()
            }
        }
    }

    private companion object {
        val TAG = AnimationRepositoryImpl::class.simpleName
        const val PAGGING_SIZE : Int = 1000
    }
}