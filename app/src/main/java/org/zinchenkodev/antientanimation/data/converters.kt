package org.zinchenkodev.antientanimation.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import org.zinchenkodev.antientanimation.data.models.LineEntity
import org.zinchenkodev.antientanimation.domain.models.Line

fun Line.toEntity(): LineEntity = LineEntity(
    startX = this.startDrawing.x,
    startY = this.startDrawing.y,
    endX = this.endDrawing.x,
    endY = this.endDrawing.y,
    color = this.color.value.toLong()
)

fun LineEntity.toDomain(): Line = Line(
    startDrawing = IntOffset(this.startX, this.startY),
    endDrawing = IntOffset(this.endX, this.endY),
    color = Color(this.color.toULong())
)
