package com.barryzea.composeapp.data.di

import com.barryzea.composeapp.data.remoteDatasource.RetrofitServiceImpl
import com.barryzea.composeapp.data.remoteDatasource.RetrofitServiceInterface
import com.barryzea.composeapp.data.repository.RepositoryImpl
import com.barryzea.composeapp.data.repository.RepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/****
 * Project ComposeApp
 * Created by Barry Zea H. on 02/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun retrofitDatasourceProvider():RetrofitServiceInterface=RetrofitServiceImpl()

    @Provides
    @Singleton
    fun repositoryProvider(retrofitService:RetrofitServiceInterface):RepositoryInterface=RepositoryImpl(retrofitService)
}
