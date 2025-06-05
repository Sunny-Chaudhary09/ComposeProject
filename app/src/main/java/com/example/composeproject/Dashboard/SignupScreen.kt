package com.example.composeproject.Dashboard

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import android.util.Patterns
import androidx.compose.ui.platform.LocalContext
import com.example.composeproject.Login.LoginActivity
import kotlin.jvm.java

@Composable
fun SignUpScreenUI(
     context: Context = LocalContext.current,
    onBackClick: () -> Unit = {},
    onSignInClick: () -> Unit = {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
                   }
) {
    var fullName by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var fullNameError by remember { mutableStateOf<String?>(null) }
    var contactNumberError by remember { mutableStateOf<String?>(null) }
    var usernameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier
                .size(28.dp)
                .clickable { onBackClick() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Create Account",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        SignUpTextField(
            placeholder = "Enter Full name",
            value = fullName,
            error = fullNameError,
            onValueChange = { fullName = it }
        )
        Spacer(modifier = Modifier.height(12.dp))

        SignUpTextField(
            placeholder = "Enter Contact Number",
            value = contactNumber,
            error = contactNumberError,
            keyboardType = KeyboardType.Phone,
            onValueChange = { contactNumber = it }
        )
        Spacer(modifier = Modifier.height(12.dp))

        SignUpTextField(
            placeholder = "Username",
            value = username,
            error = usernameError,
            onValueChange = { username = it }
        )
        Spacer(modifier = Modifier.height(12.dp))

        SignUpTextField(
            placeholder = "Enter Email address",
            value = email,
            error = emailError,
            keyboardType = KeyboardType.Email,
            onValueChange = { email = it }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "By signing up, you agree to Edflake terms of service and privacy policy.",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Clear previous errors
                fullNameError = null
                contactNumberError = null
                usernameError = null
                emailError = null

                var isValid = true

                if (fullName.isBlank()) {
                    fullNameError = "Please enter your full name"
                    isValid = false
                }

                if (contactNumber.isBlank()) {
                    contactNumberError = "Please enter contact number"
                    isValid = false
                } else if (contactNumber.length < 10) {
                    contactNumberError = "Contact must be at least 10 digits"
                    isValid = false
                }

                if (username.isBlank()) {
                    usernameError = "Please enter username"
                    isValid = false
                }

                if (email.isBlank()) {
                    emailError = "Please enter email address"
                    isValid = false
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailError = "Invalid email address"
                    isValid = false
                }

                if (isValid) {
                    // Form is valid, proceed signup logic here
                    // API call or move to next screen
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500))
        ) {
            Text("Sign up", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("Or", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_camera),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp)
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_search),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Already have an account? ", color = Color.Black)
            Text(
                text = "Sign in",
                color = Color(0xFFFFA500),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    onSignInClick()
                }
            )
        }
    }
}

@Composable
fun SignUpTextField(
    placeholder: String,
    value: String,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFFDF6E9),
                focusedContainerColor = Color(0xFFFDF6E9),
                focusedBorderColor = if (error != null) Color.Red else Color.Black,
                unfocusedBorderColor = if (error != null) Color.Red else Color.Gray,
                errorBorderColor = Color.Red
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            isError = error != null,
            singleLine = true
        )

        if (error != null) {
            Text(
                text = error,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreenUI()
}