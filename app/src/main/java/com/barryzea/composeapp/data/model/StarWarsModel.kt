package com.barryzea.composeapp.data.model

import com.google.gson.annotations.SerializedName

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 02/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
data class StarWarsModel(
    @SerializedName("count")val count:Int=0,
    @SerializedName("next")val next:String="",
    @SerializedName("previous")val prev:String="",
    @SerializedName("results")val characters:List<StarWarsCharacter> = arrayListOf()
)

data class StarWarsCharacter(
    @SerializedName("name")val name:String="",
    @SerializedName("height") val height:String="",
    @SerializedName("mass")val  mass:String="",
    @SerializedName("hair_color") val hairColor:String="",
    @SerializedName("skin_color")val skinColor:String="",
    @SerializedName("eye_color")val eyeColor:String="",
    @SerializedName("birth_year")val birthYear:String="",
    @SerializedName("gender")val gender:String="",
    @SerializedName("species")val species:List<String> = arrayListOf(),
    @Transient var specie:String="",
    @Transient var home:String="",
    @SerializedName("url")val urlPeople:String="",
    @SerializedName("homeworld")val homeWorld:String="",
    @SerializedName("films")val films:List<String> = arrayListOf(),
    @SerializedName("vehicles")val vehicles:List<String> = arrayListOf(),
    @SerializedName("starships")val starships:List<String> = arrayListOf(),
    //creamos las siguientes propiedades que no son de la API, ya que esta solo entrega url's
    //y nosotros al obtener los datos dentro de las url las setearemos en estas propiedades
    @Transient var filmsList:List<Film> = arrayListOf(),
    @Transient var vehiclesList:List<Vehicle> = arrayListOf(),
    @Transient var starshipsList:List<Starship> = arrayListOf()



)
data class HomeWorld(val name:String="")

data class Specie(
    @SerializedName("name")val name:String="",
    @SerializedName("designation")val designation:String="",
    @SerializedName("language")val language:String=""
)
data class Film(
    @SerializedName("title")val title:String="",
    @SerializedName("episode_id")val episodeId:Int=0,
    @SerializedName("director")val director:String="",
    @SerializedName("producer")val producer:String="",
    @SerializedName("release_date")val releaseDate:String=""
)

data class Vehicle(
    @SerializedName("name")val name:String ="",
    @SerializedName("model")val model:String="",
    @SerializedName("manufacturer")val manufacturer:String="",
    @SerializedName("cost_in_credits")val cost:String="",
    @SerializedName("length")val length:String="",
    @SerializedName("crew")val crew:String="",
    @SerializedName("passengers")val passengers:String="",
    @SerializedName("vehicle_class")val vehicleClass:String=""
)

data class Starship(
    @SerializedName("name")val name:String ="",
    @SerializedName("model")val model:String="",
    @SerializedName("manufacturer")val manufacturer:String="",
    @SerializedName("cost_in_credits")val cost:String="",
    @SerializedName("length")val length:String="",
    @SerializedName("crew")val crew:String="",
    @SerializedName("passengers")val passengers:String="",
    @SerializedName("starship_class")val starshipClass:String=""
)
