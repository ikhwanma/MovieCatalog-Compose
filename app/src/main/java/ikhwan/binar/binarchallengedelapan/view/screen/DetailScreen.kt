package ikhwan.binar.binarchallengedelapan.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ikhwan.binar.binarchallengedelapan.R

@Composable
fun DetailScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_tmdb),
            contentDescription = "Image",
            modifier = Modifier
                .height(175.dp)
                .width(150.dp)
        )
        Text(text = "Title", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        Text(text = "Date", fontWeight = FontWeight.Normal, fontSize = 20.sp)
    }
}