package ikhwan.binar.binarchallengedelapan.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ikhwan.binar.binarchallengedelapan.data.datastore.DataStoreManager
import ikhwan.binar.binarchallengedelapan.data.utils.MainRepository
import ikhwan.binar.binarchallengedelapan.model.users.GetUserResponseItem
import ikhwan.binar.binarchallengedelapan.model.users.PostUserResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelUser @Inject constructor(
    private val mainRepository: MainRepository,
    private val pref: DataStoreManager
) : ViewModel() {

    var getUserResponse: List<GetUserResponseItem> by mutableStateOf(listOf())
    var getUserIdResponse: List<GetUserResponseItem> by mutableStateOf(listOf())
    var getEmailState: String by mutableStateOf("")

    fun setEmail(email: String) {
        viewModelScope.launch {
            pref.setUser(email)
        }
    }

    fun getEmail(): LiveData<String> {
        return pref.getUser().asLiveData()
    }

    fun setEmailState(email: String){
        getEmailState = email
    }

    fun getUser(email: String) {
        viewModelScope.launch {
            getUserResponse = mainRepository.getUser(email)
        }
    }

    fun getUserId(id: String) {
        viewModelScope.launch {
            getUserIdResponse = listOf(mainRepository.getUserId(id))
            Log.d("userId", getUserIdResponse.toString())
        }
    }

    fun registerUser(user: PostUserResponse) {
        viewModelScope.launch {
            mainRepository.addUsers(user)
        }
    }
}