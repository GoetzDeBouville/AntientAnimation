package org.zinchenkodev.antientanimation.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["frame_id", "line_id"],
    tableName = "frame_lines",
    indices = [Index(value = ["frame_id"]), Index(value = ["line_id"])]
)
data class FrameLineCrossRef(
    @ColumnInfo("frame_id") val frameId: Long,
    @ColumnInfo("line_id") val lineId: Long
)