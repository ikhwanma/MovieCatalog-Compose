package ikhwan.binar.binarchallengedelapan.view.screen

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ikhwan.binar.binarchallengedelapan.R
import ikhwan.binar.binarchallengedelapan.model.users.GetUserResponseItem
import ikhwan.binar.binarchallengedelapan.view.RegisterActivity
import ikhwan.binar.binarchallengedelapan.view.component.ButtonMovieApp
import ikhwan.binar.binarchallengedelapan.view.component.TextButtonMovieApp
import ikhwan.binar.binarchallengedelapan.view.component.TextFieldsMovieApp
import ikhwan.binar.binarchallengedelapan.view.ui.theme.MidnightBlue
import ikhwan.binar.binarchallengedelapan.view.ui.theme.TopOnlyCorner
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelState
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser

@ExperimentalFoundationApi
@Composable
fun LoginScreen(
    viewModel: ViewModelUser,
    viewModelState: ViewModelState,
    listUser: MutableList<GetUserResponseItem>
) {
    val context = LocalContext.current

    val email = viewModelState.email
    val password = viewModelState.password
    val passVisibility = viewModelState.passVisibility

    var cek by remember {
        mutableStateOf(false)
    }

    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Image(
                painter = painterResource(id = R.drawable.img_tmdb),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(1f)
                    .width(185.dp)
                    .height(133.dp)
            )
            Card(
                modifier = Modifier
                    .weight(2f),
                shape = TopOnlyCorner.medium
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(
                        text = "Login",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 30.sp,
                        color = MidnightBlue
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    TextFieldsMovieApp(
                        values = email,
                        label = "Email",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

                    ButtonMovieApp(text = "Log In") {
                        var user: GetUserResponseItem? = null
                        for (data in listUser) {
                            if (data.email == email) {
                                cek = true
                                user = data
                                break
                            }
                        }

                        if (cek) {
                            if (password == user!!.password) {
                                viewModel.setId(user.id)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Password Salah",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(context, "Akun belum terdaftar", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    TextButtonMovieApp(
                        text = "Doesn't have account? Register",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    ) {
                        context.startActivity(Intent(context, RegisterActivity::class.java))
                    }
                }
            }
        }
    }
}
