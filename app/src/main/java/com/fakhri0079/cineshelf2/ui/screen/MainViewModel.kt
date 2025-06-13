package com.fakhri0079.cineshelf2.ui.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhri0079.cineshelf2.model.Cinema
import com.fakhri0079.cineshelf2.network.ApiStatus
import com.fakhri0079.cineshelf2.network.CinemaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Cinema>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set



     fun retrieveData(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING

            try {
                data.value = CinemaApi.service.getCinema(userId).cinema

                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure retrieve: ${e.message}")
                if(e.message == "HTTP 500 Internal Server Error"){
                    status.value = ApiStatus.EMPTY
                }else{
                    status.value = ApiStatus.FAILED
                }

            }
        }
    }

    fun saveData(
        userId: String,
        title: String,
        description: String,
        rating: Float,
        isWatched: Boolean,
        bitmap: Bitmap
    ){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = CinemaApi.service.postCinema(
                    userId,
                    title.toRequestBody("text/plain".toMediaTypeOrNull()),
                    description.toRequestBody("text/plain".toMediaTypeOrNull()),
                    rating.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                    isWatched.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                    bitmap.toMultipartBody()
                )
                if (result.message == "Cinema created successfully") {
                    Log.d("MainViewModel", "Success sending: ${result.message} $userId")
                    retrieveData(userId)
                }
                else throw Exception(result.message)
            }catch (e: Exception){
                Log.d("MainViewModel", "Failure sending: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }

    }

    private fun Bitmap.toMultipartBody() : MultipartBody.Part {
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        val requestBody = byteArray.toRequestBody("image/jpeg".toMediaTypeOrNull(),0,byteArray.size)
        return MultipartBody.Part.createFormData("image", "image.jpg", requestBody)

    }

    fun clearMessage(){ errorMessage.value = null}
}