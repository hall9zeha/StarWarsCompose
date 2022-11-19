package com.barryzea.composeapp.data.repository

import androidx.paging.PagingData
import com.barryzea.composeapp.data.model.Film
import com.barryzea.composeapp.data.model.StarWarsCharacter
import com.barryzea.composeapp.data.model.Starship
import com.barryzea.composeapp.data.model.Vehicle
import kotlinx.coroutines.flow.Flow

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 06/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
interface RepositoryInterface {
    fun getAllCharacters():Flow<PagingData<StarWarsCharacter>>
    fun getSearchedCharacters(searchText:String):Flow<PagingData<StarWarsCharacter>>
    suspend fun getSpecies(id:String)
    suspend fun getHomeWorld(id:String)
    suspend fun getFilms(id:String):List<Film>
    suspend fun getVehicles(id:String):List<Vehicle>
    suspend fun getStarships(id:String):List<Starship>
    suspend fun getCharacter(id:String):StarWarsCharacter
}