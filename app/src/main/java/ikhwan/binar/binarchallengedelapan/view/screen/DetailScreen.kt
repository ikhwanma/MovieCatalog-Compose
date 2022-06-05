package ikhwan.binar.binarchallengedelapan.view.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ikhwan.binar.binarchallengedelapan.model.credit.Cast
import ikhwan.binar.binarchallengedelapan.model.detailmovie.GetDetailMovieResponse
import ikhwan.binar.binarchallengedelapan.view.component.CastItem
import ikhwan.binar.binarchallengedelapan.view.component.TextDetail
import ikhwan.binar.binarchallengedelapan.view.component.TextTitleDetail
import ikhwan.binar.binarchallengedelapan.view.ui.theme.TopOnlyCorner

@Composable
fun DetailScreen(data: GetDetailMovieResponse, listCast: MutableList<Cast>) {

    var state by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        val baseUrlImg = "https://image.tmdb.org/t/p/w500/"
        val urlImage = baseUrlImg + data.posterPath

        AsyncImage(
            model = urlImage,
            contentDescription = "image movie",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxHeight(.6f)
                .fillMaxWidth()
        )
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Card(
                modifier = if (state) {
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.9f)
                } else {
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.5f)
                }.animateContentSize(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = LinearOutSlowInEasing
                    )
                ),
                shape = TopOnlyCorner.medium,
                elevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                ) {
                    Text(
                        text = if (state) {
                            "See few detail"
                        } else {
                            "See more detail"
                        },
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .clickable {
                                state = !state
                            }
                            .fillMaxWidth(),
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(
                                rememberScrollState()
                            )
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        if (!state){
                            Text(text = data.title, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                            Text(
                                text = "Release Date : ${convertDate(data.releaseDate)}",
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = "Duration : ${convertDuration(data.runtime)}",
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            TextOverview(data = data)
                        }
                        if (state){
                            GroupTextDetail(title = "Title", subtitle = data.title)
                            DetailSpace()
                            TextOverview(data = data)
                            DetailSpace()
                            GroupTextDetail(title = "Release Date", subtitle = convertDate(data.releaseDate))
                            DetailSpace()
                            GroupTextDetail(title = "Duration", subtitle = convertDuration(data.runtime))
                            DetailSpace()
                            TextTitleDetail(text = "Cast")
                            Divider()
                            LazyRow{
                                items(listCast){
                                    CastItem(cast = it)
                                }
                            }
                            DetailSpace()
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun DetailSpace() {
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun GroupTextDetail(title: String, subtitle: String) {
    TextTitleDetail(text = title)
    Divider()
    TextDetail(text = subtitle)
}

@Composable
fun TextOverview(data: GetDetailMovieResponse) {
    GroupTextDetail(title = "Overview", subtitle = data.overview)
}

private fun convertDuration(duration: Int): String {
    val h = duration / 60
    val m = duration % 60
    return "${h}h ${m}m"
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



