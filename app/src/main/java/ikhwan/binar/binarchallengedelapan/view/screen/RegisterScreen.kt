package ikhwan.binar.binarchallengedelapan.view.screen

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ikhwan.binar.binarchallengedelapan.R
import ikhwan.binar.binarchallengedelapan.view.component.ButtonMovieApp
import ikhwan.binar.binarchallengedelapan.view.component.TextFieldsMovieApp
import ikhwan.binar.binarchallengedelapan.view.ui.theme.MidnightBlue
import ikhwan.binar.binarchallengedelapan.view.ui.theme.TopOnlyCorner
import ikhwan.binar.binarchallengedelapan.viewmodel.StateViewModel
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser
import java.util.regex.Pattern

@Composable
fun RegisterScreen(viewModelUser: ViewModelUser, stateViewModel: StateViewModel) {
    val context = LocalContext.current


    val username = stateViewModel.username
    val email = stateViewModel.email
    val password = stateViewModel.password
    val konfPassword = stateViewModel.konfPassword
    val passVisibility = stateViewModel.passVisibility
    val konfPassVisibility = stateViewModel.konfPassVisibility

    var isUsername: Boolean by remember {
        mutableStateOf(true)
    }
    var isEmail: Boolean by remember {
        mutableStateOf(true)
    }
    var isPassword: Boolean by remember {
        mutableStateOf(true)
    }
    var isKonfPass: Boolean by remember {
        mutableStateOf(true)
    }

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
                        text = "Register",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 30.sp,
                        color = MidnightBlue
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    TextFieldsMovieApp(
                        values = username,
                        label = "Username",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                        onValueChange = {
                            isUsername = it.length >= 3
                            stateViewModel.onUsernameChanged(it)
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MidnightBlue,
                            unfocusedBorderColor = MidnightBlue
                        )
                    ) {
                        IconButton(onClick = {
                            stateViewModel.onUsernameChanged("")
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "",
                                tint = MidnightBlue
                            )
                        }
                    }
                    if (!isUsername){
                        Text(text = "Minimum 3 characters", color = Color.Red, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    TextFieldsMovieApp(
                        values = email,
                        label = "Email",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                        onValueChange = {
                            isEmail = isValidEmail(it)
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
                    if (!isEmail){
                        Text(text = "Incorrect email format", color = Color.Red, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    TextFieldsMovieApp(
                        values = password,
                        label = "Password",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                        visualTransformation = if (passVisibility) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        onValueChange = {
                            isPassword = it.length >= 6
                            isKonfPass = it == konfPassword
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
                    if (!isPassword){
                        Text(text = "Minimum 6 characters", color = Color.Red, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    TextFieldsMovieApp(
                        values = konfPassword,
                        label = "Confirm Password",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                        visualTransformation = if (konfPassVisibility) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        onValueChange = {
                            isKonfPass = it == password
                            stateViewModel.onKonfPasswordChanged(it)
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                stateViewModel.onKonfVisibilityChanged(!konfPassVisibility)
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
                    if (!isKonfPass){
                        Text(text = "Password doesn't match", color = Color.Red, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    ButtonMovieApp(text = "Register") {
                        if (isUsername && isEmail && isPassword && isKonfPass){
                            val data = viewModelUser.getUserResponse
                            var check = true
                            Log.d("iniData", data.toString())
                            if (data.isNotEmpty()){
                                if (data[0].email == email){
                                    check = false
                                }
                            }
                            if (check){
                                Toast.makeText(context, "Berhasil", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun isValidEmail(email: String): Boolean {
    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9+._%\\-]{1,256}" +
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    return emailPattern.matcher(email).matches()
}