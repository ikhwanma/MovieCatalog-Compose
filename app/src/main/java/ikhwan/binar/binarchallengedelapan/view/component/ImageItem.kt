package ikhwan.binar.binarchallengedelapan.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ikhwan.binar.binarchallengedelapan.model.movieimage.Backdrop

@Composable
fun ImageItem(data : Backdrop) {
    val baseUrlImg = "https://image.tmdb.org/t/p/w500/"
    val urlImage = baseUrlImg + data.filePath

    Column(
        modifier = Modifier
            .padding(10.dp)
            .width(200.dp)
            .height(130.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable {

                },
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp
        ) {
            Column {
                AsyncImage(
                    model = urlImage,
                    contentDescription = "image movie",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}