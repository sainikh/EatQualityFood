package com.example.mainactivity.data.api

import com.example.mainactivity.Constants.ApiState
import com.example.mainactivity.domain.model.SearchResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getData(@Url url: String) : Response<SearchResponse>
}