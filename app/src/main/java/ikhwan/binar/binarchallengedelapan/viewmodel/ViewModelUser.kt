package ikhwan.binar.binarchallengedelapan.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ikhwan.binar.binarchallengedelapan.data.utils.MainRepository
import ikhwan.binar.binarchallengedelapan.model.users.GetUserResponseItem
import ikhwan.binar.binarchallengedelapan.model.users.PostUserResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelUser @Inject constructor(private val mainRepository: MainRepository) : ViewModel(){

    var getUserResponse: List<GetUserResponseItem> by mutableStateOf(listOf())

    fun getUser(email: String) {
        viewModelScope.launch {
            getUserResponse = mainRepository.getUser(email)
        }
    }

    fun registerUser(user: PostUserResponse){
        viewModelScope.launch {
            mainRepository.addUsers(user)
        }
    }
}