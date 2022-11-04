package com.example.marvel.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters:List<MarvelCharacterEntity>)

    @Query("DELETE FROM marvelcharacterentity")
        suspend fun deleteCharacters()

    @Query("""SELECT * 
              FROM marvelcharacterentity
              WHERE LOWER(name) == LOWER(:query)
              OR LOWER(description) LIKE '%' || LOWER(:query) || '%' """)

    suspend fun searchCharacters(query: String): List<MarvelCharacterEntity>



}