package com.barryzea.composeapp.ui.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.barryzea.composeapp.R
import com.barryzea.composeapp.data.model.Film
import com.barryzea.composeapp.data.model.StarWarsCharacter
import com.barryzea.composeapp.ui.theme.Pink80
import com.barryzea.composeapp.ui.theme.Purple80
import com.barryzea.composeapp.ui.viewModel.ViewModelMain


/****
 * Project ComposeApp
 * Created by Barry Zea H. on 12/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun CharacterDetail(
    viewModel: ViewModelMain,
    backPress:()->Unit
){
    val detail by viewModel.starWarsCharacter.observeAsState(StarWarsCharacter())
    val films by viewModel.filmsLiveData.observeAsState(listOf())
    val vehicles by viewModel.vehiclesLiveData.observeAsState(listOf())
    val starships by viewModel.starshipsLivedata.observeAsState(listOf())
    viewModel.starWarsCharacter.value?.filmsList=films
    viewModel.starWarsCharacter.value?.vehiclesList=vehicles
    viewModel.starWarsCharacter.value?.starshipsList=starships

    var pbIndicatorState by remember{ mutableStateOf(false) }

    val painter = rememberImagePainter(data = R.drawable.starwars_placeholder){
        crossfade(1000)
        error(R.drawable.starwars_placeholder)
        placeholder(R.drawable.starwars_placeholder)

    }

    Scaffold {
        Box(modifier = Modifier
            .offset(y = (-200).dp)
            .fillMaxWidth()
            .height(400.dp)
            .clip(
                shape = RoundedCornerShape(10)
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Purple80, Pink80)
                )
            )
        )

    val scrollState = rememberScrollState()
    Column() {
        Row(
            modifier = Modifier
                .wrapContentSize()

        ){
            ConstraintLayout(modifier = Modifier.fillMaxWidth()){
                val (topRef, detailImageRef, nameCharacterRef, textBirth, textGender,textMass) = createRefs()
                TopAppBar(
                    title = {
                        Text(
                            "Star\nWars",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 64.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        ) },
                    modifier = Modifier
                        .background(Color.Transparent)
                        .constrainAs(topRef) {},
                    navigationIcon = {
                        IconButton(onClick = { backPress() }) {
                            Icon(painter=painterResource(R.drawable.ic_arrow_back), contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    },

                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,

                )
                Image(painter = painter,
                    contentDescription ="",
                    modifier= Modifier
                        .size(140.dp, 210.dp)
                        .constrainAs(detailImageRef) {
                            top.linkTo(topRef.bottom, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        })


            }

        }

        Column(modifier=Modifier
            .verticalScroll(
                scrollState,
                enabled = true
            )) {

          Column( modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp, 0.dp, 16.dp)) {
               Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth() ) {
                   Text(
                       text = detail.name,
                       color = Color.Black,
                       fontSize = 22.sp,
                       fontWeight = FontWeight.Bold

                   )
               }
              CharacterInfo(detail = detail)
              //progress indicator antes de cargar los detalles de naves, películas y vehículos
              if(pbIndicatorState){
                  LinearProgressIndicator(progress = 0.0F, Modifier.fillMaxWidth())}
              else{
                  LinearProgressIndicator(Modifier.fillMaxWidth())
              }
              if(films.isNotEmpty()){
                  pbIndicatorState=true
                  CharacterMovies(films = films)}
              if(vehicles.isNotEmpty()){
                  CharacterVehicle(vehicles)}
              if(starships.isNotEmpty()){
                  CharacterStarship(starships = starships)}
           }


        }

    }
    }
}