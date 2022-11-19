package com.barryzea.composeapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.barryzea.composeapp.data.model.StarWarsCharacter
import com.barryzea.composeapp.data.model.StarWarsModel
import com.barryzea.composeapp.data.remoteDatasource.RetrofitServiceInterface
import retrofit2.HttpException
import java.io.IOException

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 08/11/2022.
 * Copyright (c)  All rights reserved.
 ***/

class AllCharactersPaging(private val starWarsApi: RetrofitServiceInterface):PagingSource<Int,StarWarsCharacter>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StarWarsCharacter> {
        return try{
            val nextPage=params.key?:1
            val response=starWarsApi.starWarsApiService().getAllCharacters(nextPage)
            val starWarsModel=response.body()?: StarWarsModel()
            val endPagination= starWarsModel.characters.isEmpty() || (starWarsModel.next==null)
            LoadResult.Page(
                data = starWarsModel.characters,
                prevKey = if(nextPage==1) null else nextPage -1,
                nextKey = if(endPagination) null else nextPage + 1
            )
        }
        catch(exception:IOException){
            return LoadResult.Error(exception)
        }
        catch(exception:HttpException){
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, StarWarsCharacter>): Int? {
        return state.anchorPosition
    }
}