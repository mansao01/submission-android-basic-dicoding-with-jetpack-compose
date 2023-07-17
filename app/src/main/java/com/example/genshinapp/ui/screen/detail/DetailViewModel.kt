package com.example.genshinapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.genshinapp.data.CharacterRepository
import com.example.genshinapp.model.Character
import com.example.genshinapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: CharacterRepository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Character>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<Character>>
        get() = _uiState

    fun getCharacterById(characterId:Int){
        viewModelScope.launch {
            _uiState.value = UiState.Success(repository.getCharacterById(characterId))
        }
    }

}