package com.example.marvel.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MarvelCharacterEntity(
    val name: String,
    val description: String,
    val imageUri: String,
    @PrimaryKey val id: Int? = null
)
