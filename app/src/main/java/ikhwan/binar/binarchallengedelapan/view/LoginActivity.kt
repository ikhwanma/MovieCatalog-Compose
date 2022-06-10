package ikhwan.binar.binarchallengedelapan.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ikhwan.binar.binarchallengedelapan.data.utils.Status.*
import ikhwan.binar.binarchallengedelapan.model.users.GetUserResponseItem
import ikhwan.binar.binarchallengedelapan.view.screen.LoginScreen
import ikhwan.binar.binarchallengedelapan.view.screen.WaitingScreen
import ikhwan.binar.binarchallengedelapan.view.ui.theme.BinarChallengeDelapanTheme
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelState
import ikhwan.binar.binarchallengedelapan.viewmodel.ViewModelUser

@SuppressLint("MutableCollectionMutableState")
@ExperimentalFoundationApi
@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModelUser: ViewModelUser by viewModels()
    private val viewModelState: ViewModelState by viewModels()

    private val listUser: MutableList<GetUserResponseItem> by mutableStateOf(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BinarChallengeDelapanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    WaitingScreen()
                }
            }
        }

        installSplashScreen()

        viewModelUser.getId().observe(this@LoginActivity) {
            if (it != "") {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }else{
                setUi()
            }
        }
    }

    private fun setUi() {
        setContent {
            BinarChallengeDelapanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    LoginScreen(viewModelUser, viewModelState, listUser)
                }
            }
        }

        getAllUser()
    }

    private fun getAllUser() {
        viewModelUser.getAllUser().observe(this) {
            when (it.status) {
                SUCCESS -> {
                    listUser.clear()
                    listUser.addAll(it.data!!)
                }
                ERROR -> {
                    getAllUser()
                }
                LOADING -> {
                    Log.d("loading", "lagi loading")
                }
            }
        }
    }

}




