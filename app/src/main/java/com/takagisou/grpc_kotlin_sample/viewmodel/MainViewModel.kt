package com.takagisou.grpc_kotlin_sample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takagisou.grpc_kotlin_sample.domain.datasource.ClientDataSource
import com.takagisou.grpc_kotlin_sample.domain.datasource.ClientRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val clientRepository: ClientRepository = ClientDataSource()

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun fetch() {
        clientRepository.hello("Kotlin")
            .catch {
                Log.e("MAIN", "hello failed: $it, ${it.message}, ${it.cause}")
            }
            .onEach {
                Log.d("MAIN", "hello: $it")
                _message.postValue(it)
            }.launchIn(viewModelScope)
    }
}