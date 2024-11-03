package org.zinchenkodev.antientanimation.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class FrameWithLines(
    @Embedded val frame: FrameEntity,
    @Relation(
        parentColumn = "frame_id",
        entityColumn = "line_id",
        associateBy = Junction(FrameLineCrossRef::class)
    )
    val lines: List<LineEntity>
)