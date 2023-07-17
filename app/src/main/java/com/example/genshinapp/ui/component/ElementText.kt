package com.example.genshinapp.ui.component

import android.content.res.Resources
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.genshinapp.R
import androidx.compose.ui.text.style.TextAlign


@Composable
fun ElementText(element: String, modifier: Modifier = Modifier) {
    var colorResource: Color? = null

    colorResource = when (element) {
        "Pyro" -> colorResource(id = R.color.red)
        "Anemo" -> colorResource(id = R.color.mint_dark)
        "Dendro" -> colorResource(id = R.color.green)
        "Geo" -> colorResource(id = R.color.brown)
        "Hydro" -> colorResource(id = R.color.blue)
        "Cryo" -> colorResource(id = R.color.mint)
        else -> MaterialTheme.colorScheme.surfaceTint
    }

    Text(
        text = element,
        color = colorResource,
    )


}