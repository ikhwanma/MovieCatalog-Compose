package ikhwan.binar.binarchallengedelapan.view.screen

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ikhwan.binar.binarchallengedelapan.model.popularmovie.Result
import ikhwan.binar.binarchallengedelapan.view.LoginActivity
import ikhwan.binar.binarchallengedelapan.view.component.MovieItem
import ikhwan.binar.binarchallengedelapan.view.ui.theme.MidnightBlueVariant
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser
import java.util.*

@ExperimentalFoundationApi
@Composable
fun NowPlaying(listMovie: List<Result>, name: String, viewModelUser: ViewModelUser) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Card(shape = RoundedCornerShape(30.dp),
        modifier = Modifier.padding(horizontal = 10.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f).padding(start = 30.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Welcome, ${name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )

                IconButton(
                    onClick = {
                        viewModelUser.setId("")
                        context.startActivity(Intent(context, LoginActivity::class.java))
                    }) {
                    Icon(
                        Icons.Filled.Logout,
                        "logout icon",
                        tint = Color.Black,
                    modifier = Modifier.height(30.dp).width(30.dp))
                }
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            shape = RoundedCornerShape(30.dp)
        ) {
            Column(
                modifier = Modifier.weight(9f).fillMaxHeight(.95f),
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