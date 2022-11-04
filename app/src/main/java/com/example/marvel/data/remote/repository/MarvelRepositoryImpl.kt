package com.example.marvel.data.remote.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.example.marvel.data.local.MarvelDatabase
import com.example.marvel.data.mapper.toCharacterResult
import com.example.marvel.data.mapper.toMarvelCharacterEntity
import com.example.marvel.data.remote.MarvelApi
import com.example.marvel.data.remote.dto.MarvelDto
import com.example.marvel.domain.model.Character
import com.example.marvel.domain.repository.MarvelRepository
import com.example.marvel.util.NetworkUtils
import com.example.marvel.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelApi,
    private val db: MarvelDatabase,
    private val application: Application
):MarvelRepository {
    private val dao = db.dao

    override suspend fun getMarvelCharacters(fetchFromRemote: Boolean, query: String): Flow<Resource<List<Character>>>{
        return flow{
            emit(Resource.Loading(true))
            val localCharacters = dao.searchCharacters(query)
            emit(Resource.Success(localCharacters.map { it.toCharacterResult() }))

            val isDbEmpty = localCharacters.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache ){
                emit(Resource.Loading(false))
                return@flow
            }
            var remoteCharacters: MarvelDto?= null
            try{
                remoteCharacters = api.getMarvelCharacters()

            }catch (e:Exception){
                if (e is  java.net.UnknownHostException){
                    emit(Resource.Error("Nessuna connessione", null))
                    return@flow
                }else{
                    emit(Resource.Error("Qualcosa è andato storto", null))
                    return@flow

                }
            }


            remoteCharacters.let { it ->
                dao.deleteCharacters()
                it?.data?.results?.let { it1 -> dao.insertCharacters(it1.map { it.toMarvelCharacterEntity() }) }
            }
            emit(Resource.Success(
                data = dao
                    .searchCharacters("")
                    .map { it.toCharacterResult()}
            ))
            emit(Resource.Loading(false))
        }
    }




}