package com.example.genshinapp.di

import com.example.genshinapp.data.CharacterRepository

object Injection {

    fun provideRepository(): CharacterRepository {
        return CharacterRepository.getInstance()
    }

}