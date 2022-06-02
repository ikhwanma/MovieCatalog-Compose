package ikhwan.binar.binarchallengedelapan.view.screen

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ikhwan.binar.binarchallengedelapan.model.users.PostUserResponse
import ikhwan.binar.binarchallengedelapan.view.LoginActivity
import ikhwan.binar.binarchallengedelapan.view.component.ButtonMovieApp
import ikhwan.binar.binarchallengedelapan.view.component.TextFieldsMovieApp
import ikhwan.binar.binarchallengedelapan.view.ui.theme.MidnightBlue
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelState

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(viewModelState: ViewModelState) {
    val context = LocalContext.current

    val username = viewModelState.username
    val email = viewModelState.email
    val password = viewModelState.password
    val konfPassword = viewModelState.konfPassword
    val passVisibility = viewModelState.passVisibility
    val konfPassVisibility = viewModelState.konfPassVisibility

    Scaffold(backgroundColor = Color.White) {
        Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
            TextFieldsMovieApp(
                values = username,
                label = "Username",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                onValueChange ={
                    viewModelState.onUsernameChanged(it)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MidnightBlue,
                    unfocusedBorderColor = MidnightBlue
                )
            ) {
                IconButton(onClick = {
                    viewModelState.onUsernameChanged("")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "",
                        tint = MidnightBlue
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextFieldsMovieApp(
                values = email,
                label = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                onValueChange = {
                    viewModelState.onEmailChanged(it)
                }
            ) {
                IconButton(onClick = {
                    viewModelState.onEmailChanged("")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "",
                        tint = MidnightBlue
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextFieldsMovieApp(
                values = password,
                label = "Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                visualTransformation = if (passVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                onValueChange = {
                    viewModelState.onPasswordChanged(it)
                },
                trailingIcon = {
                    IconButton(onClick = {
                        viewModelState.onVisibilityChanged(!passVisibility)
                    }) {
                        Icon(
                            imageVector = if (passVisibility) Icons.Default.Visibility
                            else Icons.Default.VisibilityOff,
                            contentDescription = "",
                            tint = MidnightBlue
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextFieldsMovieApp(
                values = konfPassword,
                label = "Confirm Password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                visualTransformation = if (konfPassVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                onValueChange = {
                    viewModelState.onKonfPasswordChanged(it)
                },
                trailingIcon = {
                    IconButton(onClick = {
                        viewModelState.onKonfVisibilityChanged(!konfPassVisibility)
                    }) {
                        Icon(
                            imageVector = if (konfPassVisibility) Icons.Default.Visibility
                            else Icons.Default.VisibilityOff,
                            contentDescription = "",
                            tint = MidnightBlue
                        )
                    }
                }
            )

            ButtonMovieApp(text = "Update Data") {
                context.startActivity(Intent(context, LoginActivity::class.java))
            }
        }
    }
}