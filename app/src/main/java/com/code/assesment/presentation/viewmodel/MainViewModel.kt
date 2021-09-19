package com.code.assesment.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code.assesment.di.Injection
import com.code.assesment.common.Resources
import com.code.assesment.domain.model.Anime
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _data = MutableLiveData<Resources<Anime>>()
    val data: LiveData<Resources<Anime>> get() = _data
    private val dataUseCase = Injection.providesDataUseCase()

    init {
        getAnimes("naruto")
    }

    fun getAnimes(query: String) {
        viewModelScope.launch {
            dataUseCase.getAnime(query).collect {
                _data.postValue(it)


            }
        }

    }
}