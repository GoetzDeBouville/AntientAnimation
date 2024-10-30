package org.zinchenkodev.antientanimation.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset

fun Offset.toIntOffset(): IntOffset = IntOffset(this.x.toInt(), this.y.toInt())
