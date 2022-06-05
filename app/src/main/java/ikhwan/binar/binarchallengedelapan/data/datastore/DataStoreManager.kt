package ikhwan.binar.binarchallengedelapan.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    suspend fun setUser(id : String){
        context.userDataStore.edit {
            it[USER_KEY] = id
        }
    }

    fun getUser() : Flow<String> {
        return context.userDataStore.data.map {
            it[USER_KEY] ?: ""
        }
    }

    companion object {
        private const val USERDATA_NAME = "user_preferences"

        private val USER_KEY = stringPreferencesKey("user_key")

        private val Context.userDataStore by preferencesDataStore(
            name = USERDATA_NAME
        )
    }
}