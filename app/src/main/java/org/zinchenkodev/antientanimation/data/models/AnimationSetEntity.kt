package org.zinchenkodev.antientanimation.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animation_set_table")
data class AnimationSetEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("animation_set_id") val animationSetId: Long = 0
)