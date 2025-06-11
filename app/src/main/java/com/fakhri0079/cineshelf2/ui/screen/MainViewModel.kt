package com.fakhri0079.cineshelf2.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhri0079.cineshelf2.network.CinemaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    init {
        retrieveData()
    }
    private fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = CinemaApi.service.getCinema()
                Log.d("MainViewModel", "Success: $result")
            } catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}