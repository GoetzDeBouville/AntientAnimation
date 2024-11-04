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

class AnimationRepositoryImpl(private val dao: AnimationDao) : AnimationRepository {
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
        val setId = if (animationSetId == 0L) {
            try {
                dao.getLastAnimationSetId() ?: 0L
            } catch (e: SQLiteException) {
                Log.e(TAG, "Error getting last animation set id, error -> ${e.localizedMessage}")
                0L
            }
        } else {
            animationSetId
        }
        Log.i(TAG, "setId = $setId")

        val framesWithLines = try {
            dao.getFramesWithLines(setId, startIndex, PAGGING_SIZE)
        } catch (e: SQLiteException) {
            Log.e(TAG, "Error getting frames with lines, error -> ${e.localizedMessage}")
            emptyList()
        }

        Log.i(TAG, "framesWithLines = $framesWithLines")

        return framesWithLines.map {
            it.map { lineEntity ->
                lineEntity.toDomain()
            }
        }
    }

    override suspend fun getFramesCount(animationSetId: Long): Long {
        return try {
            dao.countFramesByAnimationSetId(animationSetId)
        } catch (e: SQLiteException) {
            Log.e(TAG, "Error getting frames count, error -> ${e.localizedMessage}")
            0L
        }
    }

    override suspend fun clearDbAndResetTable() {
        try {
            dao.resetAndClearAllData()
        } catch (e: SQLiteException) {
            Log.e(TAG, "Error clearing db and resetting ids, error -> ${e.localizedMessage}")
        }
    }


    private companion object {
        val TAG = AnimationRepositoryImpl::class.simpleName
        const val PAGGING_SIZE: Int = 16_384
    }
}