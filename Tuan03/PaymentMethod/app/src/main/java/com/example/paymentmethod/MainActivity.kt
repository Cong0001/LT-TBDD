package com.example.paymentmethod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

sealed class PaymentMethod(
    val title: String,
    val icon: Int
) {
    object PayPal : PaymentMethod("PayPal", R.drawable.ic_paypal)
    object GooglePay : PaymentMethod("Google Pay", R.drawable.ic_googlepay)
    object ApplePay : PaymentMethod("Apple Pay", R.drawable.ic_applepay)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PaymentScreen()
            }
        }
    }
}

@Composable
fun PaymentScreen() {
    var selected by remember { mutableStateOf<PaymentMethod?>(null) }

    val methods = listOf(
        PaymentMethod.PayPal,
        PaymentMethod.GooglePay,
        PaymentMethod.ApplePay
    )

    val scale by animateFloatAsState(
        targetValue = if (selected != null) 1.2f else 1f,
        label = "logoScale"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            painter = painterResource(
                id = selected?.icon ?: R.drawable.ic_wallet
            ),
            contentDescription = "Payment Logo",
            modifier = Modifier
                .size(160.dp)
                .scale(scale)
                .padding(bottom = 24.dp)
        )


        methods.forEach { method ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { selected = method },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor =
                        if (selected == method)
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        else
                            MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(
                        selected = selected == method,
                        onClick = { selected = method }
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = method.title,
                        modifier = Modifier.weight(1f)
                    )

                    Image(
                        painter = painterResource(id = method.icon),
                        contentDescription = method.title,
                        modifier = Modifier.size(56.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (selected != null) {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Continue")
            }
        }
    }
}
