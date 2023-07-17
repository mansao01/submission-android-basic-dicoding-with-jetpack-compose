package com.example.genshinapp.ui.screen.profile

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.genshinapp.R


@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    ProfileContent()
}

@Composable
fun ProfileContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://media.licdn.com/dms/image/C5603AQFuvHU6CV6iFA/profile-displayphoto-shrink_200_200/0/1657595297419?e=1695254400&v=beta&t=VjI3sVh-q7os-VcOYs-PufyIpiZmzmpGzhbvwRZ9hNA")
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.loading),
            modifier = modifier
                .clip(CircleShape)
                .size(126.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}