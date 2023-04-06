package com.example.retrofittest.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object {

        fun getApiService(): ApiService {
            //API RESPONSE INTERCEPTOR
            val loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            //Client
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            //Retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("https://preprodapp.tekioncloud.com/api/media-v3/u/initiate-upload/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
        const val API_KEY = "c1ba1fd1cf8a42508e771949231002"
    }
}