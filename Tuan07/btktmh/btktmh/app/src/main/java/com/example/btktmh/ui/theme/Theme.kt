package com.example.btktmh.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme()
private val LightColorScheme = lightColorScheme()

/**
 * Bài yêu cầu UI theo mẫu (light). Để tránh lệch màu do dynamicColor/dark mode,
 * theme mặc định luôn dùng LightColorScheme.
 */
@Composable
fun BktmhTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
