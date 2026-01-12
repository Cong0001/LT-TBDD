package com.example.uthnavigation.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.uthnavigation.data.OnboardingData
import com.example.uthnavigation.navigation.Screen
import com.example.uthnavigation.ui.theme.components.BottomButtons
import androidx.compose.foundation.clickable

@Composable
fun OnboardingScreen(navController: NavController) {

    var page by remember { mutableStateOf(0) }
    val item = OnboardingData.items[page]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        // Skip
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Skip",
                color = Color(0xFF1E88E5),
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )

        }

        // Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(item.imageRes),
                contentDescription = null,
                modifier = Modifier.size(220.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                item.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                item.description,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF6E6E6E)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Dot indicator
            Row {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (index == page) 10.dp else 8.dp)
                            .background(
                                if (index == page) Color(0xFF1E88E5) else Color.LightGray,
                                shape = CircleShape
                            )
                    )
                }
            }
        }

        BottomButtons(
            page = page,
            onBack = { page-- },
            onNext = { page++ },
            onFinish = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                }
            }
        )
    }
}
