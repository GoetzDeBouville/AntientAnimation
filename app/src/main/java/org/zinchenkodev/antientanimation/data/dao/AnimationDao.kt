package org.zinchenkodev.antientanimation.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import org.zinchenkodev.antientanimation.data.models.AnimationSetEntity
import org.zinchenkodev.antientanimation.data.models.AnimationSetFrameCrossRef
import org.zinchenkodev.antientanimation.data.models.FrameEntity
import org.zinchenkodev.antientanimation.data.models.FrameLineCrossRef
import org.zinchenkodev.antientanimation.data.models.LineEntity

@Dao
interface AnimationDao {
    @Insert
    suspend fun insertLine(line: LineEntity): Long

    @Insert
    suspend fun insertFrame(frame: FrameEntity): Long

    @Insert
    suspend fun insertAnimationSet(animationSet: AnimationSetEntity): Long

    @Insert
    suspend fun insertFrameLineCrossRef(crossRef: FrameLineCrossRef)

    @Insert
    suspend fun insertAnimationSetFrameCrossRef(crossRef: AnimationSetFrameCrossRef)

    @Transaction
    @Query(
        """
        SELECT frame_id FROM animation_set_frames 
        WHERE animation_set_id = :animationSetId 
        ORDER BY frame_id ASC 
        LIMIT :count OFFSET :startIndex
    """
    )
    suspend fun getFrameIdsForAnimationSet(
        animationSetId: Long,
        startIndex: Long,
        count: Int
    ): List<Long>

    @Transaction
    @Query(
        """
        SELECT * FROM lines_table 
        WHERE line_id IN (
            SELECT line_id FROM frame_lines WHERE frame_id IN (:frameIds)
        )
    """
    )
    suspend fun getLinesForFrames(frameIds: List<Long>): List<LineEntity>

    suspend fun getFramesWithLines(
        animationSetId: Long,
        startIndex: Long,
        count: Int
    ): List<List<LineEntity>> {
        val frameIds = getFrameIdsForAnimationSet(animationSetId, startIndex, count)
        return if (frameIds.isNotEmpty()) {
            frameIds.map { frameId ->
                getLinesForFrames(listOf(frameId))
            }
        } else {
            emptyList()
        }
    }

    @Query("SELECT MAX(animation_set_id) FROM animation_set_table")
    suspend fun getLastAnimationSetId(): Long?

    @Query("SELECT COUNT(*) FROM animation_set_frames WHERE animation_set_id = :animationSetId")
    suspend fun countFramesByAnimationSetId(animationSetId: Long): Long


    @Query("DELETE FROM lines_table")
    suspend fun clearLines()

    @Query("DELETE FROM frames_table")
    suspend fun clearFrames()

    @Query("DELETE FROM animation_set_frames")
    suspend fun clearAnimationSetFrames()

    @Query("DELETE FROM animation_set_table")
    suspend fun clearAnimationSets()

    suspend fun clearAllData() {
        clearAnimationSetFrames()
        clearFrames()
        clearLines()
        clearAnimationSets()
    }

    @Query("DELETE FROM sqlite_sequence WHERE name='animation_set_table'")
    suspend fun resetAnimationSetIds()

    suspend fun resetAndClearAllData() {
        clearAllData()
        resetAnimationSetIds()
    }
}