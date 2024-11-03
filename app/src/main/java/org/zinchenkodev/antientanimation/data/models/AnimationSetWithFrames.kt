package org.zinchenkodev.antientanimation.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class AnimationSetWithFrames(
    @Embedded val animationSet: AnimationSetEntity,
    @Relation(
        parentColumn = "animation_set_id",
        entityColumn = "frame_id",
        associateBy = Junction(AnimationSetFrameCrossRef::class)
    )
    val frames: List<FrameEntity>
)