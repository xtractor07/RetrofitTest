package com.example.retrofittest.networking

import com.example.retrofittest.model.CurrentWeatherResponse
import com.example.retrofittest.model.InitUploadRequest
import com.example.retrofittest.model.InitUploadResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    //Get Current weather data

    @GET ("current.json")
    fun getCurrentWeather(
        @Query("key") key: String = ApiConfig.API_KEY,
        @Query("q") city: String,
        @Query("aqi") aqi: String = "no"
    ) : Call<CurrentWeatherResponse>

    @Headers("deviceId:B4FC9575EDFC45FBBA7BCDAAA05C8968",
            "tek-siteId:-1_4" ,
            "roleId:4_Controller" ,
            "tenantName:techmotors" ,
            "tekion-api-token:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyNTA2MGViMC00OWJlLTQ2NjYtYTQzZS02M2JhMjBmYTZjZTgiLCJpYXQiOjE2ODA2NzY4NzMsInN1YiI6IjI1MDYwZWIwLTQ5YmUtNDY2Ni1hNDNlLTYzYmEyMGZhNmNlOCIsImlzcyI6IkxvZ2luU2VydmljZSIsInVubG9ja0FjY291bnQiOmZhbHNlLCJub3VuY2UiOiJmNWVlMTBhMS1iMDcwLTQwYjEtYjNkYy04NGRhOTYyZjZlNjkiLCJlbWFpbCI6InNhcmF0aC1jQHRlY2htb3RvcnMuY29tIiwiZXhwIjoxNjgwNjg0NjQ5fQ.2lAkvqHM-3sMoKX2_Y927rSabmBN1pyjYAheD6OC2fA" ,
            "tenantId:techmotors" ,
            "Content-Type:application/json" ,
            "Accept:application/json, text/plain, */*",
            "dealerId:4",
            "Referer:",
            "userId:25060eb0-49be-4666-a43e-63ba20fa6ce8",
            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 12_5_0) AppleWebKit/537.36 (KHTML, like Gecko) ReactNativeDebugger/0.13.0 Chrome/87.0.4280.141 Electron/11.4.6 Safari/537.36",
            "clientID:mobile")
    @POST("video")
     fun getData(@Body reqBody: InitUploadRequest) : Call<InitUploadResponse>
}