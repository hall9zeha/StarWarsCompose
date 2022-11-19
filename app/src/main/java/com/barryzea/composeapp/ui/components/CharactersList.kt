package com.barryzea.composeapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.barryzea.composeapp.data.model.StarWarsCharacter
import com.barryzea.composeapp.ui.viewModel.ViewModelMain

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 05/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@OptIn(ExperimentalFoundationApi::class)
fun <T:Any>LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    key:((T)->Any)?,
    itemContent:@Composable LazyItemScope.(value:T?)->Unit
){
    items(lazyPagingItems.itemCount){index->
        itemContent(lazyPagingItems[index])
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersList(
    items: LazyPagingItems<StarWarsCharacter>,
    navController: NavController,
    viewModelMain:ViewModelMain = hiltViewModel(),
    itemDetailId: (id:Int)->Unit
    ){
    val species by remember{viewModelMain.listSpecies}


    var id=""
    LazyColumn{

      items(items,
            key={item->
                item.name
            }
      ){item->
          item?.let {
               CharacterItem(item = item,itemDetailId)
          }
      }
    }
}