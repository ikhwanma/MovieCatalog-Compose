package ikhwan.binar.binarchallengedelapan.view.component

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ikhwan.binar.binarchallengedelapan.model.popularmovie.Result
import ikhwan.binar.binarchallengedelapan.view.DetailActivity

@ExperimentalFoundationApi
@Composable
fun SimilarMovieItem(movie: Result) {
    val context = LocalContext.current

    val baseUrlImg = "https://image.tmdb.org/t/p/w500/"
    val urlImage = baseUrlImg + movie.backdropPath

    Column(
        modifier = Modifier
            .padding(10.dp)
            .width(200.dp)
            .height(180.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    context.startActivity(
                        Intent(context, DetailActivity::class.java).putExtra(
                            DetailActivity.INTENT_ID,
                            movie.id
                        )
                    )
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
                        .fillMaxWidth()
                        .height(120.dp)
                )
                Text(
                    text = movie.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}