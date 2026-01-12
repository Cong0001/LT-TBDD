package com.example.thuchanh03_calculator
// 👆 nếu TH3 là project riêng thì đổi package cho đúng

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ThucHanh03Screen()
            }
        }
    }
}

@Composable
fun ThucHanh03Screen() {
    var a by remember { mutableStateOf("") }
    var b by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Thực hành 03",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = a,
            onValueChange = { a = it },
            placeholder = { Text("Số thứ nhất") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OperatorButton("+") { result = calculate(a, b, "+") }
            OperatorButton("-") { result = calculate(a, b, "-") }
            OperatorButton("*") { result = calculate(a, b, "*") }
            OperatorButton("/") { result = calculate(a, b, "/") }
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = b,
            onValueChange = { b = it },
            placeholder = { Text("Số thứ hai") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Kết quả: $result",
            fontSize = 16.sp
        )
    }
}

@Composable
fun OperatorButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray
        )
    ) {
        Text(text, fontSize = 18.sp)
    }
}

fun calculate(a: String, b: String, op: String): String {
    val x = a.toDoubleOrNull()
    val y = b.toDoubleOrNull()

    if (x == null || y == null) return "Lỗi"

    return when (op) {
        "+" -> (x + y).toString()
        "-" -> (x - y).toString()
        "*" -> (x * y).toString()
        "/" -> if (y != 0.0) (x / y).toString() else "Lỗi"
        else -> ""
    }
}
