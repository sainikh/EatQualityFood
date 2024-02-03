package com.example.mainactivity.domain.usecase

import com.example.mainactivity.Constants.ApiState
import com.example.mainactivity.domain.model.SearchResponse
import com.example.mainactivity.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchDrinksUseCase @Inject constructor(private val repository: Repository) {
    suspend fun fetchData(searchKey: String): Flow<ApiState<SearchResponse>> {
        return repository.fetchData(searchKey)
    }
}