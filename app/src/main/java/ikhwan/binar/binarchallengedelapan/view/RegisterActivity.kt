package ikhwan.binar.binarchallengedelapan.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.binarchallengedelapan.view.screen.RegisterScreen
import ikhwan.binar.binarchallengedelapan.view.ui.theme.BinarChallengeDelapanTheme
import ikhwan.binar.binarchallengedelapan.viewmodel.StateViewModel
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser

@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {

    private val viewModelUser: ViewModelUser by viewModels()
    private val stateViewModel: StateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BinarChallengeDelapanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    viewModelUser.getUser(stateViewModel.email)
                    RegisterScreen(viewModelUser, stateViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    BinarChallengeDelapanTheme {
    }
}