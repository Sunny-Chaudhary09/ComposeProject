package com.example.composeproject.Dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeproject.ui.theme.ComposeProjectTheme

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashboardUI(modifier = Modifier.safeDrawingPadding())
        }
    }
}

@Preview(showBackground = true, showSystemUi = true,)
@Composable
private fun DArhboardPreview() {
    ComposeProjectTheme {
        Surface {
            DashboardUI()
        }
    }
}
