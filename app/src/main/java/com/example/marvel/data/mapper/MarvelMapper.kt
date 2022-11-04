package com.example.marvel.data.mapper

import com.example.marvel.data.local.MarvelCharacterEntity
import com.example.marvel.data.remote.dto.MarvelDto
import com.example.marvel.data.remote.dto.Result
import com.example.marvel.data.remote.dto.Thumbnail
import com.example.marvel.domain.model.Character

fun Result.toCharacter():Character{
    val imageUrl = thumbnail.toStringFormat()
   return Character(name = name, description = description, imageUri = imageUrl)

}
fun MarvelCharacterEntity.toCharacterResult():Character{
    return Character(name = name, description = description, imageUri = imageUri)
}
fun Character.toMarvelCharacterEntity():MarvelCharacterEntity{
    return MarvelCharacterEntity(name = name, description = description, imageUri = imageUri)
}
fun Result.toMarvelCharacterEntity():MarvelCharacterEntity{
    val imageUrl = thumbnail.toStringFormat()
    return MarvelCharacterEntity(name = name, description = description, imageUri = imageUrl)
}

fun Thumbnail.toStringFormat():String{

     val httpsValue =   path.replace("http","https")

    return ("$httpsValue/portrait_medium.$extension")

}


