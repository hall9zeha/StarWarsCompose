package com.barryzea.composeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.barryzea.composeapp.R
import com.barryzea.composeapp.data.model.Starship
import com.barryzea.composeapp.ui.theme.PurpleGrey40
import com.barryzea.composeapp.ui.theme.Yellow40

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 15/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterStarship(starships:List<Starship>){
    Column(Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Text(text = "Naves", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }

    }
    LazyRow(Modifier.wrapContentHeight()){
        items(starships){starship->
            val painter= rememberImagePainter(data = R.drawable.starwars_placeholder){
                crossfade(1000)
                error(R.drawable.starwars_placeholder)
                placeholder(R.drawable.starwars_placeholder)
            }
            Card(
                Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape=MaterialTheme.shapes.medium,
            elevation = 4.dp,
            backgroundColor = MaterialTheme.colors.surface) {
                Row(Modifier.background(
                    brush= Brush.horizontalGradient(
                        colors= listOf(
                            PurpleGrey40, Yellow40
                        )
                    )
                ), verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painter, contentDescription ="",
                        Modifier.size(80.dp, 110.dp)
                            .padding(8.dp),
                        contentScale = ContentScale.Fit
                    )
                    Column(Modifier.padding(8.dp)) {
                        Text(
                            text = starship.name,
                            fontSize = 24.sp,
                            style = TextStyle(color = Color.White)
                        )
                        Row() {
                            Text(text = "Modelo ", style = TextStyle(color = Color.White))
                            Text(
                                text = starship.model,
                                style = TextStyle(color = Color.White)
                            )
                        }
                        Row() {
                            Text(text = "Longitud  ", style = TextStyle(color = Color.White))
                            Text(
                                text = starship.length,
                                style = TextStyle(color = Color.White)
                            )
                        }
                    }
                }

            }
        }
    }
}