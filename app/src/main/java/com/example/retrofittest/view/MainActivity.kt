package com.example.retrofittest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofittest.R
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.model.CurrentWeatherResponse
import com.example.retrofittest.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        mainViewModel = MainViewModel()
        initObservers()

        activityMainBinding.fetchBtn.setOnClickListener {
            fetch()
        }
    }

    private fun initObservers() {
        mainViewModel.isLoading.observe(this) {
            isLoading -> if (isLoading) activityMainBinding.weatherTv.text = resources.getString(R.string.loading)
        }
        mainViewModel.isError.observe(this) {
            isError -> if (isError) activityMainBinding.weatherTv.text = mainViewModel.errorMessage
            mainViewModel.weatherData
        }
        mainViewModel.weatherData.observe(this) {weatherData ->
            setResultText(weatherData)
        }

    }

    private fun setResultText(weatherData: CurrentWeatherResponse) {

        val result = StringBuilder("Result: \n")

        weatherData.location.let {location ->
                result.append("Name: ${location?.name}\n")
                result.append("Region: ${location?.region}\n")
                result.append("Country: ${location?.country}\n")
                result.append("Timezone ID: ${location?.tzId}\n")
                result.append("Local Time: ${location?.localtime}\n")
        }

        weatherData.current.let {current ->
            current?.condition.let {condition ->
                result.append("Condition : ${condition?.text} \n")
            }
            result.append("Celsius : ${current?.tempC} \n")
            result.append( "Fahrenheit : ${current?.tempF} \n")

            activityMainBinding.weatherTv.text = result
        }
    }

    private fun fetch() {
        if (activityMainBinding.cityEt.text.isNullOrEmpty() or activityMainBinding.cityEt.text.isNullOrBlank()){
            activityMainBinding.cityEt.error = "Field can't be null!!"
        } else {
            //get data
            mainViewModel.getWeatherData(activityMainBinding.cityEt.text.toString())
        }
    }
}