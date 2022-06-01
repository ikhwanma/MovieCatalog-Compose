package ikhwan.binar.binarchallengedelapan.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class StateViewModel : ViewModel() {
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var konfPassword by mutableStateOf("")
    var passVisibility by mutableStateOf(false)
    var konfPassVisibility by mutableStateOf(false)
    var logReg by mutableStateOf(false)

    fun onVisibilityChanged(newBoolean: Boolean) {
        passVisibility = newBoolean
    }

    fun onKonfVisibilityChanged(newBoolean: Boolean) {
        konfPassVisibility = newBoolean
    }

    fun onLogRegChanged(newBoolean: Boolean) {
        logReg = newBoolean
    }

    fun onEmailChanged(newString: String) {
        email = newString
    }

    fun onPasswordChanged(newString: String) {
        password = newString
    }
    fun onKonfPasswordChanged(newString: String) {
        konfPassword = newString
    }

    fun onUsernameChanged(newString: String){
        username = newString
    }


}