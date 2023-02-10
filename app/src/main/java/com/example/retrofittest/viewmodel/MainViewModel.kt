package com.example.retrofittest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofittest.model.CurrentWeatherResponse
import com.example.retrofittest.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("SameParameterValue", "UNUSED_EXPRESSION")
class MainViewModel : ViewModel() {

    private var _weatherData = MutableLiveData<CurrentWeatherResponse>()
    val weatherData: LiveData<CurrentWeatherResponse> get()= _weatherData

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var _isError = MutableLiveData<Boolean>()
    val isError get() = _isError

    var errorMessage: String = ""
    private set

    fun getWeatherData(city: String) {
        _isLoading.value = true
        _isError.value = false

        val client = ApiConfig.getApiService().getCurrentWeather(city = city)

        //send api request using retrofit
        client.enqueue(object : Callback<CurrentWeatherResponse> {
            override fun onResponse(
                call: Call<CurrentWeatherResponse>,
                response: Response<CurrentWeatherResponse>
            ) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    onError("Data Processing Error")
                    return
                }

                _isLoading.value = false
                _weatherData.postValue(responseBody)
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
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