package com.example.marvel.data.remote

import com.example.marvel.data.remote.dto.MarvelDto
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getMarvelCharacters(
        @Query("limit")limit: String = MarvelApi.limit,
        @Query("apikey")apikey: String = MarvelApi.API_KEY,
        @Query("ts")ts: String = MarvelApi.timeStamp,
        @Query("hash")hash: String = MarvelApi.hash(),


    ):MarvelDto


    companion object{

        const val BASE_URL:String = "https://gateway.marvel.com"

        val timeStamp = Timestamp(System.currentTimeMillis()).toString()
        const val API_KEY = "2393fd0275056e523401e310120de2cb"
        const val PRIVATE_KEY = "407295c11fc108bb7c049b3c2a722bbf33e86d1b"
        const val limit ="100"

        fun hash():String{
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val algo = MessageDigest.getInstance("MD5")
            return BigInteger(1,algo.digest(input.toByteArray())).toString(16).padStart(32,'0')

        }
    }
}