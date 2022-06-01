package ikhwan.binar.binarchallengedelapan.view.component

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ikhwan.binar.binarchallengedelapan.R
import ikhwan.binar.binarchallengedelapan.view.HomeActivity

@Composable
fun ButtonMovieApp(text: String, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}

@Composable
fun ButtonImageMovieApp(text: String, image: Painter, onClick: () -> Unit) {
    Button(
        onClick = { onClick() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = image,
                contentDescription = "Image",
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            )
            Text(text = text)
        }
    }
}

@Composable
fun TextButtonMovieApp(text: String, modifier: Modifier, style: TextStyle, onClick: () -> Unit) {
    ClickableText(
        text = AnnotatedString(text),
        onClick = { onClick() },
        modifier = modifier,
        style = style
    )
}