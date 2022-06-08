package ikhwan.binar.binarchallengedelapan.view.screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ikhwan.binar.binarchallengedelapan.model.credit.Cast
import ikhwan.binar.binarchallengedelapan.model.detailmovie.GetDetailMovieResponse
import ikhwan.binar.binarchallengedelapan.model.movieimage.Backdrop
import ikhwan.binar.binarchallengedelapan.model.popularmovie.Result
import ikhwan.binar.binarchallengedelapan.view.HomeActivity
import ikhwan.binar.binarchallengedelapan.view.component.*
import ikhwan.binar.binarchallengedelapan.view.ui.theme.BottomOnlyCorner
import ikhwan.binar.binarchallengedelapan.view.ui.theme.MidnightBlueVariant
import ikhwan.binar.binarchallengedelapan.view.ui.theme.TopOnlyCorner

@ExperimentalFoundationApi
@Composable
fun DetailScreen(
    data: GetDetailMovieResponse,
    listCast: MutableList<Cast>,
    listSimilar: MutableList<Result>,
    listImage: MutableList<Backdrop>
) {
    var state by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()

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
            verticalArrangement = if (state) {
                Arrangement.SpaceBetween
            } else {
                Arrangement.Bottom
            }
        ) {
            Card(
                modifier = if (state) {
                    Modifier
                        .fillMaxWidth()
                        .weight(.1f)
                } else {
                    Modifier
                        .fillMaxWidth()
                        .height(0.dp)
                }
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 200,
                            easing = LinearOutSlowInEasing
                        )
                    )
                    .border(
                        width = 1.dp,
                        color = MidnightBlueVariant,
                        shape = BottomOnlyCorner.medium
                    ),
                elevation = 0.dp,
                shape = BottomOnlyCorner.medium
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(data.homepage)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            Icons.Filled.TravelExplore,
                            "icon Home",
                            tint = MidnightBlueVariant,
                            modifier = Modifier
                                .weight(1f)
                                .width(30.dp)
                                .height(30.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = data.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "\"${data.tagline}\"",
                            modifier = Modifier.fillMaxWidth(),
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    IconButton(onClick = {
                        val intent = Intent()
                        intent.action = Intent.ACTION_SEND
                        intent.putExtra(Intent.EXTRA_TEXT, data.homepage)
                        intent.type = "text/plain"
                        context.startActivity(Intent.createChooser(intent, "Share To:"))
                    }) {
                        Icon(
                            Icons.Filled.Share,
                            "icon share",
                            tint = MidnightBlueVariant,
                            modifier = Modifier
                                .weight(1f)
                                .width(30.dp)
                                .height(30.dp)
                        )
                    }
                }
            }

            Card(
                modifier = if (state) {
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(.9f)
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
                            "Click here to see fewer detail"
                        } else {
                            "Click here to see more detail"
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
                            .verticalScroll(state = rememberScrollState(1))
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        if (!state) {
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
                            Text(
                                text = "Overview",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 23.sp
                            )
                            Divider()
                            Text(
                                text = data.overview,
                                fontWeight = FontWeight.Normal,
                                fontSize = 18.sp,
                                color = Color.Black,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Justify
                            )
                        }
                        if (state) {
                            TextOverview(data = data)
                            DetailSpace()
                            GroupTextDetail(
                                title = "Release Date",
                                subtitle = convertDate(data.releaseDate)
                            )
                            DetailSpace()
                            GroupTextDetail(
                                title = "Duration",
                                subtitle = convertDuration(data.runtime)
                            )
                            DetailSpace()
                            TextTitleDetail(text = "Preview")
                            Divider()
                            LazyRow {
                                items(listImage) {
                                    ImageItem(data = it)
                                }
                            }
                            DetailSpace()
                            TextTitleDetail(text = "Cast")
                            Divider()
                            LazyRow {
                                items(listCast) {
                                    CastItem(cast = it)
                                }
                            }
                            DetailSpace()
                            TextTitleDetail(text = "Similar Movie")
                            Divider()
                            LazyRow {
                                items(listSimilar){
                                    SimilarMovieItem(movie = it)
                                }
                            }
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



