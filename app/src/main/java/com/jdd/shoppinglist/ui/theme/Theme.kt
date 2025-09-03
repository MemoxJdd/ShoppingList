package com.jdd.shoppinglist.ui.theme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Typography
import androidx.compose.material.Shapes
import androidx.compose.material.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


// Renk paletleri
private val DarkColorPalette = darkColors(
    primary = Color(0xFFBB86FC),
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF6200EE),
    primaryVariant = Color(0xFF3700B3),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    // Diğer opsiyonel renkler: error, onError vs.
)

// Tipografi
val AppTypography = Typography(
    h1 = TextStyle(fontSize = 96.sp),
    h2 = TextStyle(fontSize = 60.sp),
    h3 = TextStyle(fontSize = 48.sp),
    h4 = TextStyle(fontSize = 34.sp),
    h5 = TextStyle(fontSize = 24.sp),
    h6 = TextStyle(fontSize = 20.sp),
    subtitle1 = TextStyle(fontSize = 16.sp),
    subtitle2 = TextStyle(fontSize = 14.sp),
    body1 = TextStyle(fontSize = 16.sp),
    body2 = TextStyle(fontSize = 14.sp),
    button = TextStyle(fontSize = 14.sp),
    caption = TextStyle(fontSize = 12.sp),
    overline = TextStyle(fontSize = 10.sp)
)

// Şekiller
private val AppShapes = Shapes(
    small = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
)

// Tema composable'ı
@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}