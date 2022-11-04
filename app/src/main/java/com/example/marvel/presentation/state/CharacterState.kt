package com.example.marvel.presentation.state

import com.example.marvel.domain.model.Character
import com.example.marvel.util.Resource

data class CharactersState(
        val isLoading:Boolean = false,
        val charactersList: List<Character>? = null,
        val error: String? = null,
        val characterQuery: String? = null,
        val isRefreshing: Boolean = false
        ){
}