package ikhwan.binar.binarchallengedelapan.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.binarchallengedelapan.R
import ikhwan.binar.binarchallengedelapan.view.component.ButtonMovieApp
import ikhwan.binar.binarchallengedelapan.view.component.TextButtonMovieApp
import ikhwan.binar.binarchallengedelapan.view.component.TextFieldsMovieApp
import ikhwan.binar.binarchallengedelapan.view.screen.LoginScreen
import ikhwan.binar.binarchallengedelapan.view.screen.RegisterScreen
import ikhwan.binar.binarchallengedelapan.view.ui.theme.BinarChallengeDelapanTheme
import ikhwan.binar.binarchallengedelapan.view.ui.theme.MidnightBlue
import ikhwan.binar.binarchallengedelapan.view.ui.theme.TopOnlyCorner
import ikhwan.binar.binarchallengedelapan.viewmodel.StateViewModel
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser

@ExperimentalFoundationApi
@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModelUser: ViewModelUser by viewModels()
    private val stateViewModel: StateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BinarChallengeDelapanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    viewModelUser.getUser(stateViewModel.email)
                    LoginScreen(viewModelUser, stateViewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    BinarChallengeDelapanTheme {

    }
}



