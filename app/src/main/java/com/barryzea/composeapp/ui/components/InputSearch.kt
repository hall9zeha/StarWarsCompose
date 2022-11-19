package com.barryzea.composeapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 09/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputSearch(
    text:String,
    onTextChange:(String)->Unit,
    onSearchClicked:(String)->Unit,
    onCloseClicked:()->Unit
){
    val keyboardController = LocalSoftwareKeyboardController.current
    Column( modifier= Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ){
        TextField(value = text, onValueChange ={value-> onTextChange(value)},
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .border(
                    width = 1.5f.dp,
                    color = MaterialTheme.colors.primary,
                    shape = CircleShape
                ),
            textStyle = TextStyle(
                color=MaterialTheme.colors.primary,
                fontSize = 16.sp
           ),
            leadingIcon = {
                Icon(Icons.Default.Search,
                    contentDescription ="",
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp),
                tint = MaterialTheme.colors.primary)
            },
            trailingIcon = {
                if(text.isNotEmpty()){
                    IconButton(onClick = {
                        keyboardController?.hide()
                        onTextChange("")
                        onCloseClicked()
                    }){
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(24.dp),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
            },
            keyboardOptions =  KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if(text.trim().isNotEmpty()){
                        keyboardController?.hide()
                        onSearchClicked(text)
                    }
                }
            ),
            singleLine=true,
            colors=TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.primary,
                cursorColor = MaterialTheme.colors.primary,
                leadingIconColor = Color.White,
                trailingIconColor = Color.White,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
            )
    }

}