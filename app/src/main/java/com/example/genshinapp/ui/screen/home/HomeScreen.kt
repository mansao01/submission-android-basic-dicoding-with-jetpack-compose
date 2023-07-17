@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.genshinapp.ui.screen.home

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.genshinapp.model.Character
import com.example.genshinapp.ui.component.CharacterItem
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.genshinapp.ViewModelFactory
import com.example.genshinapp.di.Injection
import com.example.genshinapp.ui.component.TextCenter
import com.example.genshinapp.ui.common.UiState


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
    navigateToProfile: () -> Unit,

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
                    navigateToDetail = navigateToDetail,
                    navigateToProfile = navigateToProfile
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
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "GenshinApp")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        navigateToProfile()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        }, content = {
            LazyColumn(modifier = modifier.padding(it)) {
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
    )

}