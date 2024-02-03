package com.example.mainactivity.presentation.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mainactivity.Constants.ApiState
import com.example.mainactivity.Constants.Constants
import com.example.mainactivity.Constants.Network
import com.example.mainactivity.domain.model.Drink
import com.example.mainactivity.domain.model.DrinkOnHome
import com.example.mainactivity.domain.model.SearchResponse
import com.example.mainactivity.domain.repository.Repository
import com.example.mainactivity.domain.usecase.FetchDrinksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val useCase: FetchDrinksUseCase,
    private val application: Application
) : ViewModel() {
    private val _drinksList = MutableStateFlow<List<DrinkOnHome>?>(null)
    val drinksList: StateFlow<List<DrinkOnHome>?> = _drinksList

    private val _actualDrink = MutableStateFlow<List<Drink>?>(null)
    val actualDrink: StateFlow<List<Drink>?> = _actualDrink


    private val _loading = MutableStateFlow<Boolean?>(false)
    val loading: StateFlow<Boolean?> = _loading

    private val _failure = MutableStateFlow<String?>(null)
    val failure: StateFlow<String?> = _failure
    fun fetchData(search: String) {
        if (Network.isInternetConnected(applicaion = application)) {

            viewModelScope.launch {
                val listOfDrinks = useCase.fetchData(search)
                listOfDrinks.collect() { apiResponse ->
                    when (apiResponse) {
                        is ApiState.Success -> {
                            _loading.value = false
                            _actualDrink.value = apiResponse.data.drinks
                            _drinksList.value = _actualDrink.value!!.map { drink ->
                                DrinkOnHome(
                                    strDrink = drink.strDrink,
                                    strDrinkThumb = drink.strDrinkThumb
                                )
                            }
                        }

                        is ApiState.Loading -> {
                            _loading.value = true
                        }

                        is ApiState.Failure -> {
                            _loading.value = false
                            _failure.value = apiResponse.msg
                        }
                    }
                }
            }
        } else {
            _failure.value = Constants.NO_NETWORK
        }
    }

    //Fetch all the drinks for the first time
    init {
        fetchData("search.php?s=")
    }
}