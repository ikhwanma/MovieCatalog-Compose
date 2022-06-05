package ikhwan.binar.binarchallengedelapan.view.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TextDetail(text : String) {
    Text(
        text = text,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = Color.Black,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Justify
    )
}

@Composable
fun TextTitleDetail(text: String) {
    Text(text = text, fontWeight = FontWeight.ExtraBold, fontSize = 23.sp)
}