package com.example.retrofittest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofittest.model.CurrentWeatherResponse
import com.example.retrofittest.model.InitUploadRequest
import com.example.retrofittest.model.InitUploadResponse
import com.example.retrofittest.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("SameParameterValue", "UNUSED_EXPRESSION")
class MainViewModel : ViewModel() {

    private var _weatherData = MutableLiveData<CurrentWeatherResponse>()
    private var _uploadResponse = MutableLiveData<InitUploadResponse>()
    val weatherData: LiveData<CurrentWeatherResponse> get()= _weatherData

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var _isError = MutableLiveData<Boolean>()
    val isError get() = _isError

    var errorMessage: String = ""
    private set

    fun fetchData(reqBody: InitUploadRequest) {
        _isLoading.value = true
        _isError.value = false

        val client = ApiConfig.getApiService().getData(reqBody)

        //send api request using retrofit
        client.enqueue(object : Callback<InitUploadResponse> {
            override fun onResponse(
                call: Call<InitUploadResponse>,
                response: Response<InitUploadResponse>
            ) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    onError("Data Processing Error")
                    return
                }
                Log.d("ResponseBody", responseBody.status)
            }

            override fun onFailure(call: Call<InitUploadResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun onError(s: String) {
        if(s.isBlank() or s.isEmpty()) "Unknown Error"
        else {
            s
        }
        errorMessage = StringBuilder("ERROR: ")
            .append("$s some data may not display properly").toString()

        _isError.value = true
        _isLoading.value = false
    }
}