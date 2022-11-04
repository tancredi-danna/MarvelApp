package com.example.marvel.domain.repository

import android.app.Application
import com.example.marvel.domain.model.Character
import com.example.marvel.util.Resource
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    suspend fun getMarvelCharacters(
        fetchFromRemote: Boolean,
        query: String): Flow<Resource<List<Character>>>
}