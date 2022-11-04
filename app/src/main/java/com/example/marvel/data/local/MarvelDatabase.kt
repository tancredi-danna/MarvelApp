package com.example.marvel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MarvelCharacterEntity::class],
    version = 1
)
abstract class MarvelDatabase: RoomDatabase() {
    abstract val dao: MarvelDao
}