package com.barryzea.composeapp.ui.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.barryzea.composeapp.common.getIdFromUrl
import com.barryzea.composeapp.data.model.*
import com.barryzea.composeapp.data.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 06/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@HiltViewModel
class ViewModelMain @Inject constructor(private val repository: RepositoryInterface):ViewModel() {

   /* private val _charactersList=MutableStateFlow<PagingData<StarWarsCharacter>>(PagingData.empty())
    val charactersList=_charactersList*/

    val allCharactersList=repository.getAllCharacters()

    val starWarsCharacter= MutableLiveData<StarWarsCharacter>()
    val filmsLiveData=MutableLiveData<List<Film>>()
    val vehiclesLiveData=MutableLiveData<List<Vehicle>>()
    val starshipsLivedata=MutableLiveData<List<Starship>>()
    private val _foundCharactersList=MutableStateFlow<PagingData<StarWarsCharacter>>(PagingData.empty())
    val foundCharacterList=_foundCharactersList

    private val _isSearch= mutableStateOf(false)
    val isSearch=_isSearch
    private val _searchText= mutableStateOf("")
    val searchText=_searchText
    private var _listSpecies:MutableState<List<Specie>> = mutableStateOf(arrayListOf())

    val listSpecies=_listSpecies

    fun searchCharacter(textSearch:String){
        _isSearch.value=true
        viewModelScope.launch{
            repository.getSearchedCharacters(textSearch).cachedIn(viewModelScope).collect{
                _foundCharactersList.value=it
            }

        }
    }
    fun getCharacter(id:String){
        viewModelScope.launch{
            val response=repository.getCharacter(id)
            starWarsCharacter.postValue(response)
            if(response.films.isNotEmpty()){
                var films = listOf<Film>()
                response.films.forEach {
                  films=repository.getFilms(it.getIdFromUrl())

                }
               filmsLiveData.postValue(films)
            }
            if(response.vehicles.isNotEmpty()){
                var vehicles= listOf<Vehicle>()
                response.vehicles.forEach {
                    vehicles =repository.getVehicles(it.getIdFromUrl())
               }
                vehiclesLiveData.postValue(vehicles)
            }
            if(response.starships.isNotEmpty()){
                var starships= listOf<Starship>()
                response.starships.forEach {
                   starships =repository.getStarships(it.getIdFromUrl())
                }
                starshipsLivedata.postValue(starships)
            }

        }
    }
    fun updateSearchText(text:String){
        _searchText.value=text
    }

    fun setSearch(state: Boolean) {
        _isSearch.value=state
    }

}