package org.zinchenkodev.antientanimation.data

import androidx.room.Database
import androidx.room.RoomDatabase
import org.zinchenkodev.antientanimation.data.dao.AnimationDao
import org.zinchenkodev.antientanimation.data.models.AnimationSetEntity
import org.zinchenkodev.antientanimation.data.models.AnimationSetFrameCrossRef
import org.zinchenkodev.antientanimation.data.models.AnimationSetWithFrames
import org.zinchenkodev.antientanimation.data.models.FrameEntity
import org.zinchenkodev.antientanimation.data.models.FrameLineCrossRef
import org.zinchenkodev.antientanimation.data.models.FrameWithLines
import org.zinchenkodev.antientanimation.data.models.LineEntity

@Database(
    entities = [
        LineEntity::class,
        FrameEntity::class,
        AnimationSetEntity::class,
        FrameLineCrossRef::class,
        AnimationSetFrameCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animationDao(): AnimationDao
}
