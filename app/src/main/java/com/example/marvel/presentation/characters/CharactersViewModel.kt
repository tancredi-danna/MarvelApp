package com.example.marvel.presentation.characters

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.data.remote.repository.MarvelRepositoryImpl
import com.example.marvel.presentation.state.CharactersState
import com.example.marvel.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val marvelRepository: MarvelRepositoryImpl,private val application: Application):ViewModel() {

    var state by mutableStateOf(CharactersState())

    init {

        getCharacters(fetchFromRemote = true)

    }
    fun getCharacters(fetchFromRemote:Boolean = false, query: String? = null) {
        viewModelScope.launch {
            if (isInternetAvailable(application)){
                loadCharacters(fetchFromRemote, query)

            }else{
                loadCharacters(false, query)
            }
        }
    }

    private suspend fun loadCharacters(fetchFromRemote: Boolean, query: String?) {
        marvelRepository.getMarvelCharacters(fetchFromRemote, query?:"").collect {
            if (it is Resource.Success) {
                state = state.copy(charactersList = it.data, error = null)
            } else if (it is Resource.Loading) {
                state = state.copy(isLoading = it.isLoading, error = null)
            } else {
                state = state.copy(error = it.message, isLoading = false)
            }
        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }
}
