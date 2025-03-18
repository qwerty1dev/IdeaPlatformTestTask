package com.example.ideaplatformtesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.ideaplatformtesttask.ui.ProductListScreen
import com.example.ideaplatformtesttask.ui.theme.IdeaPlatformTestTaskTheme
import com.example.ideaplatformtesttask.viewmodel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val roomViewModel: RoomViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IdeaPlatformTestTaskTheme {
                ProductListScreen(roomViewModel)
            }
        }
    }
}

