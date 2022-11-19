package com.barryzea.composeapp.ui



import androidx.compose.foundation.layout.Column

import androidx.compose.material.TopAppBar

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.barryzea.composeapp.R

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 05/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@Preview
@Composable
fun Toolbar(){
    Column(){
        val context= LocalContext.current
        TopAppBar(title = { Text (text = context.getString(R.string.app_name), color = Color.White)}
            ,backgroundColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        )

    }
}