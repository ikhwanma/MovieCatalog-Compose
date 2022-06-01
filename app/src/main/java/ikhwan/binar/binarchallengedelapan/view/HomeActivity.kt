package ikhwan.binar.binarchallengedelapan.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.binarchallengedelapan.R
import ikhwan.binar.binarchallengedelapan.view.component.ButtonImageMovieApp
import ikhwan.binar.binarchallengedelapan.view.component.MovieItem
import ikhwan.binar.binarchallengedelapan.view.ui.theme.BinarChallengeDelapanTheme
import ikhwan.binar.binarchallengedelapan.view.ui.theme.MidnightBlueVariant
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelMovie
import ikhwan.binar.binarchallengelima.model.popularmovie.ResultMovie


@ExperimentalFoundationApi
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModelMovie: ViewModelMovie by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BinarChallengeDelapanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ListMovie(listMovie = viewModelMovie.getPopularResponse)
                    viewModelMovie.getPopularMovie("63be5170b074455a7fba3a528aeea4ce")
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun ListMovie(listMovie: List<ResultMovie>) {
    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .weight(0.75f)
                    .padding(horizontal = 30.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Welcome, Ikhwan!", color = Color.White, fontSize = 20.sp)
            }
            Card(
                modifier = Modifier
                    .weight(8.5f),
                shape = RoundedCornerShape(30.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LazyVerticalGrid(cells = GridCells.Adaptive(150.dp), content = {
                        item(span = { GridItemSpan(8) }) {
                            Text(
                                text = "Popular Movie",
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
            Row(
                modifier = Modifier
                    .weight(0.75f)
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ButtonImageMovieApp(
                    text = "Popular",
                    image = painterResource(id = R.drawable.ic_baseline_local_movies_24)
                ) {

                }
                ButtonImageMovieApp(
                    text = "Now Play",
                    image = painterResource(id = R.drawable.ic_baseline_local_movies_24)
                ) {

                }
                ButtonImageMovieApp(
                    text = "Profile",
                    image = painterResource(id = R.drawable.ic_baseline_person_24)
                ) {

                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    BinarChallengeDelapanTheme {

    }
}