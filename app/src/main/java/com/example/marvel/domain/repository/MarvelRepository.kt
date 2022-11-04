package com.example.marvel.domain.model

interface MarvelRepository {

    suspend fun getMarvelCharacters(
        fetchFromRemote: Boolean,
        query: String)
}