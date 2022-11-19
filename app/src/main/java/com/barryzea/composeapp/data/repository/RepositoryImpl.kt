package com.barryzea.composeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.barryzea.composeapp.common.getIdFromUrl
import com.barryzea.composeapp.data.model.*
import com.barryzea.composeapp.paging.AllCharactersPaging
import com.barryzea.composeapp.data.remoteDatasource.RetrofitServiceInterface
import com.barryzea.composeapp.paging.SearchCharactersPaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 06/11/2022.
 * Copyright (c)  All rights reserved.
 ***/

class RepositoryImpl (private val retrofitServiceInterface: RetrofitServiceInterface):RepositoryInterface {
    var starWarsCharacter=StarWarsCharacter()
    private var speciesList= arrayListOf<Specie>()
    private var vehiclesList= arrayListOf<Vehicle>()
    private var filmsList= arrayListOf<Film>()
    private var starshipsList= arrayListOf<Starship>()

    override  fun getAllCharacters(): Flow<PagingData<StarWarsCharacter>> {
    val configPager= PagingConfig(10)
        return Pager(
            config = configPager,
            pagingSourceFactory ={
                AllCharactersPaging(starWarsApi = retrofitServiceInterface)
          }
        ).flow
    }

    override fun getSearchedCharacters(searchText: String): Flow<PagingData<StarWarsCharacter>> {
        val configPager=PagingConfig(10)
        return Pager(
            configPager,
            pagingSourceFactory = {
                SearchCharactersPaging(retrofitServiceInterface,searchText)
            }
        ).flow
    }

    override suspend fun getCharacter(id: String): StarWarsCharacter {
        //limpiamos la lista de especies para cada caracter
        speciesList.clear()
        vehiclesList.clear()
        filmsList.clear()
        starshipsList.clear()
        return withContext(Dispatchers.IO){
            val response =retrofitServiceInterface.starWarsApiService().getCharacter(id)
            starWarsCharacter=response.body()?:StarWarsCharacter()
            if(response.isSuccessful){
                response.body()?.species?.let{
                    it.forEach {url->
                        getSpecies(url.getIdFromUrl())
                    }
                }
                response.body()?.homeWorld?.let{
                    getHomeWorld(it.getIdFromUrl())
                }

            }
            starWarsCharacter
        }
    }

    override suspend fun getSpecies( id:String) {

        withContext(Dispatchers.IO){
            val response=retrofitServiceInterface.starWarsApiService().getSpecies(id)
            val specieCharacter=response.body()?:Specie()
            speciesList.add(specieCharacter)
            starWarsCharacter.specie=specieCharacter.name

        }


    }

    override suspend fun getHomeWorld(id: String) {
        withContext(Dispatchers.IO){
            val response = retrofitServiceInterface.starWarsApiService().getHomeWorld(id)
            val homeWorld=response.body()?: HomeWorld()
            starWarsCharacter.home=homeWorld.name
        }
    }

    override suspend fun getFilms(id: String) :List<Film> {
        return withContext(Dispatchers.IO){
            val response = retrofitServiceInterface.starWarsApiService().getFilms(id)
            val film=response.body()?: Film()
            filmsList.add(film)
            filmsList

        }
    }

    override suspend fun getVehicles(id: String):List<Vehicle> {
        return withContext(Dispatchers.IO){
            val response = retrofitServiceInterface.starWarsApiService().getVehicles(id)
            val vehicle=response.body()?:Vehicle()
            vehiclesList.add(vehicle)
           vehiclesList
        }
    }

    override suspend fun getStarships(id: String):List<Starship> {
       return  withContext(Dispatchers.IO) {
            val response = retrofitServiceInterface.starWarsApiService().getStarships(id)
            val starship = response.body() ?: Starship()
            starshipsList.add(starship)
            starshipsList
        }
    }
}