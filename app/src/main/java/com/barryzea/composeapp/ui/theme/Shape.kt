package com.barryzea.composeapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 01/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
data class Shape(
    val default: RoundedCornerShape = RoundedCornerShape(0.dp),
    val small: RoundedCornerShape = RoundedCornerShape(4.dp),
    val medium: RoundedCornerShape = RoundedCornerShape(8.dp),
    val large: RoundedCornerShape = RoundedCornerShape(16.dp)
)
val LocalShape = compositionLocalOf { Shape() }
val MaterialTheme.shapeScheme:Shape
    @Composable
    @ReadOnlyComposable
    get() = LocalShape.current

