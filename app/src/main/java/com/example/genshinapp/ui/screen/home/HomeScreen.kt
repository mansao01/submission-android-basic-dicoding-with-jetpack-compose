package com.example.genshinapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.genshinapp.model.Character
import com.example.genshinapp.ui.component.CharacterItem
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.genshinapp.ViewModelFactory
import com.example.genshinapp.di.Injection
import com.example.genshinapp.ui.common.TextCenter
import com.example.genshinapp.ui.common.UiState


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                TextCenter(text = "Please Wait")
                viewModel.getAllCharacters()
            }

            is UiState.Success -> {
                HomeContent(
                    characters = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }

            is UiState.Error -> {
                TextCenter(text = "Something is wrong")
            }
        }
    }
}

@Composable
fun HomeContent(
    characters: List<Character>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.padding(top = 8.dp)) {
        items(characters) { data ->
            CharacterItem(
                name = data.name,
                element = data.element,
                photoUrl = data.picture,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                })
        }
    }
}