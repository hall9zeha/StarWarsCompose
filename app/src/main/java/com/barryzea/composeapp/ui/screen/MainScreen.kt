package com.barryzea.composeapp.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.barryzea.composeapp.data.model.StarWarsCharacter
import com.barryzea.composeapp.ui.components.InputSearch
import com.barryzea.composeapp.ui.components.ProgressIndicator
import com.barryzea.composeapp.ui.theme.ComposeAppTheme
import com.barryzea.composeapp.ui.viewModel.ViewModelMain

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 05/11/2022.
 * Copyright (c)  All rights reserved.
 ***/

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable

fun MainScreen( navController:NavController, viewModelMain:ViewModelMain= hiltViewModel()){
    val characters = viewModelMain.allCharactersList.collectAsLazyPagingItems()
    val charactersFound=viewModelMain.foundCharacterList.collectAsLazyPagingItems()

    var showDialog by remember{ mutableStateOf(false)}


    val searchText by viewModelMain.searchText
    val isSearch by viewModelMain.isSearch
    ComposeAppTheme {
        if(characters.itemCount>0){
            showDialog=true
        }

        Scaffold(topBar = {MyToolbar()},
        ) {

           Column() {
                    InputSearch(searchText,
                        onTextChange ={
                            viewModelMain.updateSearchText(it)
                    } , onSearchClicked ={
                        viewModelMain.searchCharacter(it)
                    },
                    onCloseClicked = {
                        viewModelMain.setSearch(false)
                    })
                    CharactersList(navController=navController, viewModelMain = viewModelMain,
                        items = if(isSearch) charactersFound else characters){id->
                        val route="detail_screen/${id}"
                        navController.navigate(route){
                            launchSingleTop=true
                        }
                    }
                }
        }

        //loading dialog
        if (!showDialog) {
            Dialog(
                
                //al cambiar el estado de showDialog el alert dialog se cerrará
                onDismissRequest = { showDialog = true },
                DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
            ) {
               Box(

                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp)),
                   contentAlignment = Alignment.Center

                ) {
                        ProgressIndicator()

                   }

            }
        }
    }
}
@Preview
@Composable
fun MyToolbar(){
    Toolbar()
}
