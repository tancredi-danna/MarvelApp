package com.example.marvel.util

import com.example.marvel.domain.model.Character

object MatchUtil {

    fun findMatch(singleCharacter: Character, searchedText: String):Boolean{
        val singleStringDescription = singleCharacter.description.split(" ")

        var foundMatchDescription:Boolean = false
        for (i in singleStringDescription){
            if (i.contains(searchedText, true)){
                foundMatchDescription = true
            }
        }

        val singleStringName = singleCharacter.name.split(" ")
        var foundMatchName:Boolean = false
        for (i in singleStringName){
            if (i.contains(searchedText, true)){
                foundMatchName = true
            }
        }
        return foundMatchDescription || foundMatchName
    }
}