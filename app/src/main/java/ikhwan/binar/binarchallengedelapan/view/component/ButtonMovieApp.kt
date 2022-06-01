package ikhwan.binar.binarchallengedelapan.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
fun TextButtonMovieApp(text: String, modifier: Modifier, style: TextStyle, onClick: () -> Unit) {
    ClickableText(
        text = AnnotatedString(text),
        onClick = { onClick() },
        modifier = modifier,
        style = style
    )
}