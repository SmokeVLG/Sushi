package com.maxden.sushi.viewmodels.home

import androidx.lifecycle.*
import com.maxden.sushi.repository.HomeRepository
import kotlinx.coroutines.*

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    //For loading screen => progress bar
    enum class ApiStatus {LOADING, DONE, ERROR}
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    //repository
    val users = repository.users

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                repository.refreshKitsAndItems()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

}