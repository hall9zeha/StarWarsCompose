package com.barryzea.composeapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.barryzea.composeapp.ui.theme.Purple80

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 15/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@Preview
@Composable
fun ProgressIndicator(){
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            Modifier.padding(16.dp,8.dp,16.dp,4.dp),
            color = Purple80,
            strokeWidth = Dp(value=4F),
            )
        Text(text = "Cargando",Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp))

    }
}