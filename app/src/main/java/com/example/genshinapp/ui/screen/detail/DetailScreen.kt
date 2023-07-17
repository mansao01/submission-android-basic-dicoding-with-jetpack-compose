package com.example.genshinapp.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.genshinapp.ViewModelFactory
import com.example.genshinapp.di.Injection
import com.example.genshinapp.ui.component.TextCenter
import com.example.genshinapp.ui.common.UiState
import com.example.genshinapp.ui.component.ElementText

@Composable
fun DetailScreen(
    playerId: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                TextCenter(text = "Please Wait")
                viewModel.getCharacterById(playerId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    name = data.name,
                    element = data.element,
                    region = data.region,
                    picture = data.picture,
                    onBackClick = { navigateBack() }
                )
            }

            is UiState.Error -> {
                TextCenter(text = "Something is wrong")
            }
        }

    }
}

@Composable
fun DetailContent(
    name: String,
    element: String,
    region: String,
    picture: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clickable { onBackClick() })
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = picture,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(MaterialTheme.shapes.medium)


            )
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )

            ElementText(
                element = element
            )

            Text(
                text = region,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,

                )
        }
    }

}