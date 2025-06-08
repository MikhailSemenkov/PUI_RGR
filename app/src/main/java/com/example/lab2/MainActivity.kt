package com.example.lab2

import DataApi
import LoginApi
import RegisterAPI
import RetrofitInstance
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab2.ui.theme.Lab2Theme


class MainActivity : ComponentActivity() {
    private val retrofitInstance = RetrofitInstance.getInstance()
    private val loginApi = retrofitInstance.create(LoginApi::class.java)
    private val registerApi = retrofitInstance.create(RegisterAPI::class.java)
    private val dataApi = retrofitInstance.create(DataApi::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            if (Build.VERSION.SDK_INT > 9) {
                val policy =
                    ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
            }
            val navController = rememberNavController()
            Lab2Theme {
                NavHost(
                    navController = navController,
                    startDestination = "home",
                    builder = {
                        composable(route = "home") {
                            HomePage(navController)
                        }
                        composable(route = "login") {
                            LoginPage(navController, loginApi, LocalContext.current)
                        }
                        composable(route = "register") {
                            RegisterPage(navController, registerApi, LocalContext.current)
                        }
                        composable(route = "data") {
                            DataPage(navController, dataApi)
                        }
                    }
                )
            }
        }
    }
}
