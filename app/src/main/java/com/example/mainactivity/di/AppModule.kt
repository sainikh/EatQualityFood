package com.example.mainactivity.di

import com.example.mainactivity.Constants.Constants
import com.example.mainactivity.data.api.ApiService
import com.example.mainactivity.domain.repository.Repository
import com.example.mainactivity.domain.repository.RepositoryImpl
import com.example.mainactivity.domain.usecase.FetchDrinksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance() : ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api : ApiService): Repository {
       return RepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideFetchDrinksUseCase(repo : Repository): FetchDrinksUseCase {
        return FetchDrinksUseCase(repo)
    }




}