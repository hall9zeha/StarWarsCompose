package com.barryzea.composeapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.barryzea.composeapp.ui.components.CharacterDetail
import com.barryzea.composeapp.ui.theme.ComposeAppTheme
import com.barryzea.composeapp.ui.viewModel.ViewModelMain

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 05/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController:NavController,
        detailId:String,
        viewModelMain: ViewModelMain = hiltViewModel()){
    ComposeAppTheme {
        Scaffold() {
            Column {
                CharacterDetail(viewModel = viewModelMain) {
                    navController.navigate("main_screen"){
                        launchSingleTop=true
                    }
                }
                LaunchedEffect(Unit) { viewModelMain.getCharacter(detailId) }

            }
        }
    }
}