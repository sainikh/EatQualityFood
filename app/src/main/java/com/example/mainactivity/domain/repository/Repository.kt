package com.example.mainactivity.domain.repository

import com.example.mainactivity.Constants.ApiState
import com.example.mainactivity.domain.model.Drink
import com.example.mainactivity.domain.model.SearchResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun fetchData(data : String) : Flow<ApiState<SearchResponse>>
}