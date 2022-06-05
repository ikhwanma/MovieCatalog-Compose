package ikhwan.binar.binarchallengedelapan.view.component

import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ikhwan.binar.binarchallengedelapan.view.ui.theme.*
import ikhwan.binar.binarchallengedelapan.model.popularmovie.Result
import ikhwan.binar.binarchallengedelapan.view.DetailActivity

@Composable
fun MovieItem(movie: Result) {
    val context = LocalContext.current
    val baseUrlImg = "https://image.tmdb.org/t/p/w500/"
    val urlImage = baseUrlImg + movie.posterPath

    val date = convertDate(movie.releaseDate)

    val rating = movie.voteAverage
    val color = when (rating) {
        in 7.0..10.0 -> {
            GreenRating
        }
        in 4.0..7.0 -> {
            YellowRating
        }
        else -> {
            RedRating
        }
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .width(150.dp)
            .height(290.dp), horizontalAlignment = Alignment.CenterHorizontally
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp),
                ) {
                    AsyncImage(
                        model = urlImage,
                        contentDescription = "image movie",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 150.dp, start = 10.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .border(width = 2.dp, color = color, RoundedCornerShape(20.dp)),
                            backgroundColor = MidnightBlue,
                            shape = RoundedCornerShape(20.dp),
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(7.dp)
                            ) {
                                Text(
                                    text = rating.toString(),
                                    color = color,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                }
                Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                    Text(
                        text = movie.title,
                        color = MidnightBlueVariant,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = date,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }

            }
        }
    }
}

private fun convertDate(data: String): String {
    if (data != "") {
        val list = data.split("-").toTypedArray()
        val day = list[2]
        var month = ""
        val year = list[0]

        when {
            list[1] == "01" -> {
                month = "Jan"
            }
            list[1] == "02" -> {
                month = "Feb"
            }
            list[1] == "03" -> {
                month = "Mar"
            }
            list[1] == "04" -> {
                month = "Apr"
            }
            list[1] == "05" -> {
                month = "May"
            }
            list[1] == "06" -> {
                month = "Jun"
            }
            list[1] == "07" -> {
                month = "Jul"
            }
            list[1] == "08" -> {
                month = "Aug"
            }
            list[1] == "09" -> {
                month = "Sep"
            }
            list[1] == "10" -> {
                month = "Oct"
            }
            list[1] == "11" -> {
                month = "Nov"
            }
            list[1] == "12" -> {
                month = "Dec"
            }
        }
        return "$month $day, $year"
    }
    return ""
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    BinarChallengeDelapanTheme {

    }
}