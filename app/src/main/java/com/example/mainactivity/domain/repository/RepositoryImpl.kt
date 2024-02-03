package com.example.mainactivity.domain.repository

import com.example.mainactivity.Constants.ApiState
import com.example.mainactivity.data.api.ApiService
import com.example.mainactivity.domain.model.Drink
import com.example.mainactivity.domain.model.SearchResponse
import com.example.mainactivity.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor( val api : ApiService) : Repository {
    override suspend fun fetchData(data: String): Flow<ApiState<SearchResponse>> {
            return ApiResult {
                api.getData(data)
            }

    }

}