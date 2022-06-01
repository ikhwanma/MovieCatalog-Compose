package ikhwan.binar.binarchallengedelapan.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.binarchallengedelapan.view.screen.HomeScreen
import ikhwan.binar.binarchallengedelapan.view.ui.theme.BinarChallengeDelapanTheme
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelMovie
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelState
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser

@ExperimentalFoundationApi
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModelMovie: ViewModelMovie by viewModels()
    private val viewModelUser: ViewModelUser by viewModels()
    private val viewModelState: ViewModelState by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BinarChallengeDelapanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val apiKey = "63be5170b074455a7fba3a528aeea4ce"
                    HomeScreen(viewModelMovie, viewModelUser, viewModelState, apiKey)

                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    BinarChallengeDelapanTheme {

    }
}