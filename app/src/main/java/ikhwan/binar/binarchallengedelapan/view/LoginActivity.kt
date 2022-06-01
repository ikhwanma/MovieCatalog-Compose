package ikhwan.binar.binarchallengedelapan.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.binarchallengedelapan.view.screen.LoginScreen
import ikhwan.binar.binarchallengedelapan.view.ui.theme.BinarChallengeDelapanTheme
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelState
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser

@ExperimentalFoundationApi
@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModelUser: ViewModelUser by viewModels()
    private val viewModelState: ViewModelState by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BinarChallengeDelapanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    viewModelUser.getUser(viewModelState.email)
                    LoginScreen(viewModelUser, viewModelState)
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



