package ikhwan.binar.binarchallengedelapan.view.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import ikhwan.binar.binarchallengedelapan.view.ui.theme.MidnightBlue

@Composable
fun TextFieldsMovieApp(
    values: String,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions,
    colors : TextFieldColors =  TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MidnightBlue,
        unfocusedBorderColor = MidnightBlue
    ),
    onValueChange: (String) -> Unit,
    trailingIcon : @Composable () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = values,
        onValueChange = {
            onValueChange(it)
        },
        colors = colors,
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        label = { Text(text = label, color = MidnightBlue) },
        singleLine = true,
        trailingIcon = {
            trailingIcon()
        },
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
        )
    )
}