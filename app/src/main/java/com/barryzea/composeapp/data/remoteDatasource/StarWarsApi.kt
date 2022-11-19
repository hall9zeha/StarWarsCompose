package com.barryzea.composeapp.data.remoteDatasource

import com.barryzea.composeapp.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 02/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
interface StarWarsApi {
    @GET("people/")
    suspend fun getAllCharacters(@Query("page")page:Int): Response<StarWarsModel>

    @GET("species/{id}/")
    suspend fun getSpecies(@Path("id")id:String):Response<Specie>
    @GET("planets/{id}/")
    suspend fun getHomeWorld(@Path("id")id:String):Response<HomeWorld>

    @GET("films/{id}/")
    suspend fun getFilms(@Path("id")id:String):Response<Film>
    @GET("vehicles/{id}/")
    suspend fun getVehicles(@Path("id")id:String):Response<Vehicle>
    @GET("starships/{id}/")
    suspend fun getStarships(@Path("id")id:String):Response<Starship>
    @GET("people/")
    suspend fun getSearchingCharacters(@Query("search")textSearch:String): Response<StarWarsModel>

    @GET("people/{personId}")
    suspend fun getCharacter(@Path("personId")personId:String):Response<StarWarsCharacter>

}