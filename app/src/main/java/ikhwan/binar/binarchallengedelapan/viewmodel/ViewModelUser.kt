package ikhwan.binar.binarchallengedelapan.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ikhwan.binar.binarchallengelima.data.utils.MainRepository
import ikhwan.binar.binarchallengelima.data.utils.Resource
import ikhwan.binar.binarchallengelima.model.users.GetUserResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ViewModelUser @Inject constructor(private val mainRepository: MainRepository) : ViewModel(){

    var getUserResponse: List<GetUserResponseItem> by mutableStateOf(listOf())

    fun getUser(email: String) {
        viewModelScope.launch {
            getUserResponse = mainRepository.getUser(email)
        }
    }
}