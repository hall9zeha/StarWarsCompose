package com.barryzea.composeapp.common

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 07/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
fun String.getIdFromUrl():String{
    return if(this.endsWith("/")){
        this.dropLast(1).takeLastWhile { it.isDigit() }
    }else{
        this.takeLastWhile { it.isDigit()}
    }
}