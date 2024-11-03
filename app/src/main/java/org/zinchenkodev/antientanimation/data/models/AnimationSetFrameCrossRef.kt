package org.zinchenkodev.antientanimation.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["animation_set_id", "frame_id"],
    tableName = "animation_set_frames",
    indices = [Index(value = ["animation_set_id"]), Index(value = ["frame_id"])]
)
data class AnimationSetFrameCrossRef(
    @ColumnInfo("animation_set_id") val animationSetId: Long,
    @ColumnInfo("frame_id") val frameId: Long
)