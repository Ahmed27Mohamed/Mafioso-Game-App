package com.a2004256_ahmedmohamed.mafiosogame.ux_ui

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.a2004256_ahmedmohamed.mafiosogame.R
import com.a2004256_ahmedmohamed.mafiosogame.Screen
import com.a2004256_ahmedmohamed.mafiosogame.model.Player
import com.a2004256_ahmedmohamed.mafiosogame.model.Role
import com.a2004256_ahmedmohamed.mafiosogame.ui.theme.MafiosoGameTheme

@Composable
fun PlayerRoleScreen(
    player: Player,
    onNext: () -> Unit
) {
    var showData by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.click)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {

        // 📌 اسم اللاعب أعلى الشاشة
        Text(
            text = player.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.TopCenter),
            color = Color.White
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (!showData) {
                // 🔒 شاشة إخفاء البيانات + رسالة تمرير الهاتف
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "مرّر الهاتف إلى اللاعب ${player.name} دون النظر ",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )

                    Spacer(Modifier.height(30.dp))

                    IconButton(
                        onClick = {
                            mediaPlayer.start()
                            showData = true
                        },
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.LightGray, shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "عرض بياناتك",
                            tint = Color.Black,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    Text("اضغط هنا لعرض بياناتك", color = Color.White)
                }
            } else {
                // 👤 بيانات اللاعب تظهر عند الضغط
                Text(
                    text = if (player.role == Role.MURDERER) "🔴 أنت المافيوسو" else "🟢 أنت بريء",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(Modifier.height(20.dp))

                Text("👤 اسمك: ${player.name}", color = Color.White)
                Text("👔 وظيفتك: ${player.job}", color = Color.White)
                Text("📍 مكانك: ${player.location}", color = Color.White)

                Spacer(Modifier.height(20.dp))
                Text(
                    "اقرأ المعلومات اللي تحت بصوت عالي:",
                    textAlign = TextAlign.Center,
                    color = Color.Red
                )
                Text("💣 الدافع: ${player.motive}", color = Color.White)
                Text("😳 السر: ${player.secret}", color = Color.White)
                Text("⚠️ معلومة غريبة: ${player.suspiciousInfo}", color = Color.White)

                Spacer(Modifier.height(40.dp))

                Button(
                    onClick = {
                        showData = false
                        onNext()
                    }
                ) {
                    Text("تم ✅", color = Color.White)
                }
            }
        }
    }
}