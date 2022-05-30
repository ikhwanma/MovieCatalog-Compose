package ikhwan.binar.binarchallengedelapan

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.binarchallengedelapan.ui.theme.BinarChallengeDelapanTheme
import ikhwan.binar.binarchallengedelapan.ui.theme.MidnightBlue
import ikhwan.binar.binarchallengedelapan.ui.theme.TopOnlyCorner
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModelUser: ViewModelUser by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BinarChallengeDelapanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    viewModelUser.getUser(loginViewModel.email)
                    LoginScreen(viewModelUser, loginViewModel)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(viewModel: ViewModelUser, loginViewModel: LoginViewModel) {
    val context = LocalContext.current

    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_img_logo),
                contentDescription = "App Logo",
                modifier = Modifier.weight(1f),
                colorFilter = ColorFilter.tint(Color.White)
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
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = MidnightBlue
                    )
                    OutlinedTextField(value = loginViewModel.email,
                        onValueChange = {
                            loginViewModel.onEmailChanged(it)
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MidnightBlue,
                            unfocusedBorderColor = MidnightBlue
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text(text = "Email", color = MidnightBlue) },
                        trailingIcon = {
                            IconButton(onClick = {
                                loginViewModel.onEmailChanged("")
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "",
                                    tint = MidnightBlue
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(value = loginViewModel.password,
                        onValueChange = {
                            loginViewModel.onPasswordChanged(it)
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MidnightBlue,
                            unfocusedBorderColor = MidnightBlue
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = if (loginViewModel.passVisibility) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        label = { Text(text = "Password", color = MidnightBlue) },
                        singleLine = true,
                        trailingIcon = {
                            IconButton(onClick = {
                                val bool = loginViewModel.passVisibility
                                loginViewModel.onVisibilityChanged(!bool)
                            }) {
                                Icon(
                                    imageVector = if (loginViewModel.passVisibility) Icons.Default.Visibility
                                    else Icons.Default.VisibilityOff,
                                    contentDescription = "",
                                    tint = MidnightBlue
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            val data = viewModel.getUserResponse
                            val user = data[0]
                            if (data.isNotEmpty()) {
                                if (loginViewModel.email == user.email) {
                                    if (loginViewModel.password == user.password) {
                                        Toast.makeText(
                                            context,
                                            "Login Berhasil",
                                            Toast.LENGTH_SHORT
                                        ).show()
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
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "LogIn")
                    }
                }
            }
        }
    }
}

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passVisibility by mutableStateOf(false)

    fun onVisibilityChanged(newBoolean: Boolean){
        passVisibility = newBoolean
    }

    fun onEmailChanged(newString: String) {
        email = newString
    }

    fun onPasswordChanged(newString: String) {
        password = newString
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    BinarChallengeDelapanTheme {

    }
}