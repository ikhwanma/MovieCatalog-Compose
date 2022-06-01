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
import ikhwan.binar.binarchallengedelapan.view.HomeActivity
import ikhwan.binar.binarchallengedelapan.view.RegisterActivity
import ikhwan.binar.binarchallengedelapan.view.component.ButtonMovieApp
import ikhwan.binar.binarchallengedelapan.view.component.TextButtonMovieApp
import ikhwan.binar.binarchallengedelapan.view.component.TextFieldsMovieApp
import ikhwan.binar.binarchallengedelapan.view.ui.theme.MidnightBlue
import ikhwan.binar.binarchallengedelapan.view.ui.theme.TopOnlyCorner
import ikhwan.binar.binarchallengedelapan.viewmodel.StateViewModel
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser

@ExperimentalFoundationApi
@Composable
fun LoginScreen(viewModel: ViewModelUser, stateViewModel: StateViewModel) {
    val context = LocalContext.current

    val email = stateViewModel.email
    val password = stateViewModel.password
    val passVisibility = stateViewModel.passVisibility

    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_img_logo),
                contentDescription = "App Logo",
                modifier = Modifier.weight(1f)
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
                            stateViewModel.onEmailChanged(it)
                        }
                    ) {
                        IconButton(onClick = {
                            stateViewModel.onEmailChanged("")
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
                            stateViewModel.onPasswordChanged(it)
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                stateViewModel.onVisibilityChanged(!passVisibility)
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
                        context.startActivity(
                            Intent(
                                context,
                                HomeActivity::class.java
                            )
                        )
                        val data = viewModel.getUserResponse
                        if (data.isNotEmpty()) {
                            val user = data[0]
                            if (stateViewModel.email == user.email) {
                                if (password == user.password) {

                                } else {
                                    Toast.makeText(
                                        context,
                                        "Password Salah",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Akun belum terdaftar",
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
                        style = TextStyle(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                    ) {
                        context.startActivity(Intent(context, RegisterActivity::class.java))
                    }
                }
            }
        }
    }
}
