package ikhwan.binar.binarchallengedelapan.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ikhwan.binar.binarchallengedelapan.data.datastore.DataStoreManager
import ikhwan.binar.binarchallengedelapan.data.utils.MainRepository
import ikhwan.binar.binarchallengedelapan.data.utils.Resource
import ikhwan.binar.binarchallengedelapan.model.users.GetUserResponseItem
import ikhwan.binar.binarchallengedelapan.model.users.PostUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelUser @Inject constructor(
    private val mainRepository: MainRepository,
    private val pref: DataStoreManager
) : ViewModel() {

    var getUserResponse: List<GetUserResponseItem> by mutableStateOf(listOf())
    var getUserIdResponse: List<GetUserResponseItem> by mutableStateOf(listOf())
    var getIdState: String by mutableStateOf("")

    fun setId(id: String) {
        viewModelScope.launch {
            pref.setUser(id)
        }
    }

    fun getId(): LiveData<String> {
        return pref.getUser().asLiveData()
    }

    fun setIdState(id: String){
        getIdState = id
    }

    fun getUser(email: String) {
        viewModelScope.launch {
            getUserResponse = mainRepository.getUser(email)
        }
    }

    fun getAllUser() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(mainRepository.getAllUser()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occured!"))
        }
    }

    fun getUserId(id: String) {
        viewModelScope.launch {
            getUserIdResponse = listOf(mainRepository.getUserId(id))
        }
    }

    /*fun getUserId(id: String) {
        viewModelScope.launch {
            getUserIdResponse = listOf(mainRepository.getUserId(id))
            Log.d("userId", getUserIdResponse.toString())
        }
    }*/

    fun registerUser(user: PostUserResponse) {
        viewModelScope.launch {
            mainRepository.addUsers(user)
        }
    }
}