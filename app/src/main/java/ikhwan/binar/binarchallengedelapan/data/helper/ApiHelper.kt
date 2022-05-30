package ikhwan.binar.binarchallengedelapan.data.helper

import ikhwan.binar.binarchallengedelapan.data.networking.ApiService
import javax.inject.Inject
import javax.inject.Named

class ApiHelper @Inject constructor( @Named("User") private val apiServiceUser: ApiService) {
    suspend fun getUser(email : String) = apiServiceUser.getUser(email)
}