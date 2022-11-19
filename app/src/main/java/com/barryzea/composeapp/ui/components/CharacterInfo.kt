package com.barryzea.composeapp.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.barryzea.composeapp.data.model.StarWarsCharacter

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 13/11/2022.
 * Copyright (c)  All rights reserved.
 ***/


@Composable
fun CharacterInfo(
    detail:StarWarsCharacter
){
    Log.e("STARSHIPS", detail.starshipsList.toString() )
    ConstraintLayout(modifier = Modifier.wrapContentSize()) {
        val (textBirth,textGender, textMass, textSkin,textHome,textHeight,textHair, textEyes,textSpecie) = createRefs()

        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textBirth) {
                    top.linkTo(parent.top, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                }){
            Column(){
                Text(text = "Nacimiento", fontWeight = FontWeight.Bold)
                Text(text = detail.birthYear)
            }
        }
        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textGender) {
                    top.linkTo(textBirth.bottom, margin = 8.dp)
                    start.linkTo(textBirth.start)
                }){
            Column(){
                Text(text = "Género", fontWeight = FontWeight.Bold)
                Text(text = detail.gender)
            }
        }

        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textMass) {
                    top.linkTo(textGender.bottom, margin = 8.dp)
                    start.linkTo(textBirth.start)
                }){
            Column(){
                Text(text = "Masa", fontWeight = FontWeight.Bold)
                Text(text = detail.mass)
            }
        }
        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textSkin) {
                    top.linkTo(textMass.bottom, margin = 8.dp)
                    start.linkTo(textBirth.start)
                }){
            Column() {
                Text(text="Color de piel", fontWeight = FontWeight.Bold)
                Text(text = detail.skinColor)
            }
        }
        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textHome) {
                    top.linkTo(textSkin.bottom, margin = 8.dp)
                    start.linkTo(textBirth.start)
                }){
            Column() {
                Text(text="Lugar de origen", fontWeight = FontWeight.Bold)
                Text(text = detail.home)
            }

        }
        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textHeight) {
                    top.linkTo(parent.top, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                    start.linkTo(textBirth.end, margin = 8.dp)
                }){
            Column(){
                Text(text = "Altura", fontWeight = FontWeight.Bold)
                Text(text = detail.height)
            }
        }

        Row(
            modifier = Modifier
                .padding(4.dp)
                .constrainAs(textHair) {
                    top.linkTo(textHeight.bottom, margin = 8.dp)
                    start.linkTo(textHeight.start)

                }
        ){
            Column() {
                Text(text = "Color de pelo", fontWeight = FontWeight.Bold)
                Text(detail.hairColor)
            }
        }
        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textEyes) {
                    top.linkTo(textHair.bottom, margin = 8.dp)
                    start.linkTo(textHeight.start)

                }) {
            Column() {
                Text(text = "Color de ojos", fontWeight = FontWeight.Bold)
                Text(text = detail.eyeColor)
            }
        }
        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textSpecie) {
                    top.linkTo(textEyes.bottom, margin = 8.dp)
                    start.linkTo(textHeight.start)

                }) {
            Column() {
                Text(text = "Especie", fontWeight = FontWeight.Bold)
                Text(text = detail.specie.ifEmpty { "n/a" })
            }
        }
    }
}