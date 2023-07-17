package com.example.genshinapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.genshinapp.R


@Composable
fun CharacterItem(
    name: String,
    element: String,
    photoUrl: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(modifier = modifier.fillMaxWidth()) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.loading),
                modifier = Modifier
                    .size(60.dp)
            )
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(text = name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Text(text = element, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }

}

@Preview(showBackground = true, )
@Composable
fun CharacterItemPreview() {
    CharacterItem("Hutao", "Pyro", "")
}