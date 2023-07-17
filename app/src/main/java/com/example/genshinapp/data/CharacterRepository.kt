package com.example.genshinapp.data

import com.example.genshinapp.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CharacterRepository {
    private val characters = mutableListOf<Character>()
    init {
        if (characters.isEmpty()){
            DummyDataSource.characterList.forEach{
                characters.add(it)
            }
        }
    }

    fun getAllCharacter():Flow<List<Character>>{
        return flowOf(characters)
    }

    fun getCharacterById(characterId:Int):Character{
        return characters.first{
            it.id == characterId
        }
    }

    companion object {
        @Volatile
        private var instance: CharacterRepository? = null

        fun getInstance(): CharacterRepository =
            instance ?: synchronized(this) {
                CharacterRepository().apply {
                    instance = this
                }
            }

    }
}

