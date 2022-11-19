package com.barryzea.composeapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.barryzea.composeapp.R
import com.barryzea.composeapp.common.getIdFromUrl
import com.barryzea.composeapp.data.model.StarWarsCharacter
import com.barryzea.composeapp.ui.theme.*

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 05/11/2022.
 * Copyright (c)  All rights reserved.
 ***/

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterItem(item: StarWarsCharacter,
    onClick:(id:Int)->Unit
    ) {

    val painter= rememberImagePainter(data = R.drawable.starwars_placeholder){

    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick(item.urlPeople.getIdFromUrl().toInt())},
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Purple40, Pink40
                      )
                )
            ),

            verticalAlignment = Alignment.CenterVertically,

            ) {
            Image(
                painter = painter, contentDescription = null,
                modifier = Modifier
                    .size(80.dp, 120.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Fit
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = item.name, fontSize = 24.sp,
                    style = TextStyle(Color.White)
                )
               /* Row {
                    Text(
                        text = "Especie: ",
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                    Text(
                        text = item.specie,
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }*/
            }
        }

    }
}