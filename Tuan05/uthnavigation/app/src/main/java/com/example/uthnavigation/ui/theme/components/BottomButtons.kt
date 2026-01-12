package com.example.uthnavigation.ui.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

@Composable
fun BottomButtons(
    page: Int,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        if (page > 0) {
            Button(
                onClick = onBack,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E88E5)
                ),
                modifier = Modifier.size(52.dp), // to hơn chút
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "←",
                    color = Color.White,
                    fontSize = 22.sp, // 🔥 to, rõ
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Spacer(modifier = Modifier.width(52.dp))
        }


        Button(
            onClick = {
                if (page == 2) onFinish() else onNext()
            },
            shape = RoundedCornerShape(30),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E88E5)
            ),
            modifier = Modifier
                .height(52.dp)
                .width(180.dp)
        ) {
            Text(
                if (page == 2) "Get Started" else "Next",
                color = Color.White
            )
        }
    }
}
