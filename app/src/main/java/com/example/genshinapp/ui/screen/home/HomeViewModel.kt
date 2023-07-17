package com.example.genshinapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.genshinapp.data.CharacterRepository
import com.example.genshinapp.model.Character
import com.example.genshinapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: CharacterRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Character>>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<Character>>>
        get() = _uiState

    fun getAllCharacters() {
        viewModelScope.launch {
            repository.getAllCharacter()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { characters ->
                    _uiState.value = UiState.Success(characters)
                }
        }
    }
}