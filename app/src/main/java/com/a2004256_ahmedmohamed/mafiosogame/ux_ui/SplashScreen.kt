package com.a2004256_ahmedmohamed.mafiosogame.ux_ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.a2004256_ahmedmohamed.mafiosogame.R

@Composable
fun SplashScreen(navController: NavController) {

    // 🔹 تأثير التكبير (Zoom)
    var startAnimation by remember { mutableStateOf(false) }
    val scale = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.8f,
        animationSpec = tween(durationMillis = 1200)
    )

    // 🔹 انتقال تلقائي بعد 2.5 ثانية
    LaunchedEffect(true) {
        startAnimation = true
        delay(2500)
        navController.navigate("setup") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Black, Color.DarkGray)
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Game Icon",
                modifier = Modifier
                    .size(100.dp)
                    .scale(scale.value)
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Mafioso 🔎",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.scale(scale.value)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "تحقيق، غموض، ومافيوسو",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.LightGray
            )
        }
    }
}