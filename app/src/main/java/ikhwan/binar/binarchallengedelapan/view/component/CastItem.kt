package ikhwan.binar.binarchallengedelapan.view.component

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ikhwan.binar.binarchallengedelapan.model.credit.Cast

@Composable
fun CastItem(cast: Cast) {

    val baseUrlImg = "https://image.tmdb.org/t/p/w500/"
    val urlImage = baseUrlImg + cast.profilePath

    Column(
        modifier = Modifier
            .padding(10.dp)
            .width(150.dp)
            .height(270.dp),
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
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Column(modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp)) {
                    Text(
                        text = cast.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = cast.character,
                        color = Color.Gray,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }

}