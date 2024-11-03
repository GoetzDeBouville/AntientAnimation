package org.zinchenkodev.antientanimation.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "frames_table")
data class FrameEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("frame_id") val frameId: Long = 0
)
