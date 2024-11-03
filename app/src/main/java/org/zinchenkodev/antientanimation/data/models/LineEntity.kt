package org.zinchenkodev.antientanimation.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lines_table")
data class LineEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("line_id") val lineId: Long = 0,
    @ColumnInfo("start_x") val startX: Int,
    @ColumnInfo("start_y") val startY: Int,
    @ColumnInfo("end_x") val endX: Int,
    @ColumnInfo("end_y") val endY: Int,
    @ColumnInfo("color") val color: Long
)