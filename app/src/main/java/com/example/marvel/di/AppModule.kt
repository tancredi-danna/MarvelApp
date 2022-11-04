package com.example.marvel.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marvel.MarvelApplication
import com.example.marvel.data.local.MarvelDatabase
import com.example.marvel.data.remote.MarvelApi
import com.example.marvel.data.remote.repository.MarvelRepositoryImpl
import com.example.marvel.domain.repository.MarvelRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi():MarvelApi{
      return Retrofit
            .Builder()
            .baseUrl(MarvelApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BASIC }).build())
            .build()
            .create()

    }
    @Provides
    @Singleton
    fun provideDb(application: Application): MarvelDatabase {
        return Room.databaseBuilder(
            application,
            MarvelDatabase::class.java,
            "marvel.db").build()
    }
    @Provides
    @Singleton
    fun provideRepo(api: MarvelApi, db:MarvelDatabase, application: Application):MarvelRepositoryImpl{
        return MarvelRepositoryImpl(api,db, application)

    }

}