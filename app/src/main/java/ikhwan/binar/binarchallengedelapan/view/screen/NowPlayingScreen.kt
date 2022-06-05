package ikhwan.binar.binarchallengedelapan.view.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ikhwan.binar.binarchallengedelapan.view.component.MovieItem
import ikhwan.binar.binarchallengedelapan.view.ui.theme.MidnightBlueVariant
import ikhwan.binar.binarchallengedelapan.model.popularmovie.Result
import java.util.*

@ExperimentalFoundationApi
@Composable
fun NowPlaying(listMovie: List<Result>, name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Welcome, ${name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}!",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, bottom = 10.dp)
        )
        Card(
            shape = RoundedCornerShape(30.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(.95f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LazyVerticalGrid(cells = GridCells.Adaptive(150.dp), content = {
                    item(span = { GridItemSpan(8) }) {
                        Text(
                            text = "Now Playing",
                            fontSize = 25.sp,
                            color = MidnightBlueVariant,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    items(listMovie.size) {
                        MovieItem(movie = listMovie[it])
                    }
                })
            }
        }
    }
}