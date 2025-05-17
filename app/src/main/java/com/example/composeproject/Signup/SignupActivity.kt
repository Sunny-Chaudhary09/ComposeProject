package com.example.composeproject.Signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeproject.Login.LoginScreenUi

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navCOntroler = rememberNavController()
            Scaffold(modifier = Modifier) {
                NavHost(
                    modifier = Modifier.padding(it),
                    navController = navCOntroler,
                    startDestination = "signup"
                ) {
                    composable("signup") {
                        SignUpScreenUI()
                    }
                    composable("login_screen") {
                        LoginScreenUi()
                    }
                }
            }
        }
    }
}
