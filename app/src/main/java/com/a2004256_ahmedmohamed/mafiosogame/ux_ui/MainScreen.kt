package com.a2004256_ahmedmohamed.mafiosogame.ux_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.a2004256_ahmedmohamed.mafiosogame.Screen
import com.a2004256_ahmedmohamed.mafiosogame.generate.assignRoles
import com.a2004256_ahmedmohamed.mafiosogame.model.Player
import com.a2004256_ahmedmohamed.mafiosogame.model.Role
import com.a2004256_ahmedmohamed.mafiosogame.ui.theme.MafiosoGameTheme
import com.a2004256_ahmedmohamed.mafiosogame.viewmodel.GameViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: GameViewModel) {

    var playersNames by remember {
        mutableStateOf(listOf("", "", "", ""))
    }

    var murdererCount by remember { mutableStateOf(1) }
    val caseData = assignRoles(playersNames, murdererCount)

    val isValid =
        playersNames.size >= 4 &&
                playersNames.all { it.isNotBlank() } &&
                murdererCount < playersNames.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .navigationBarsPadding()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "🔎 إعداد القضية",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(Modifier.height(20.dp))

        Text("👥 اللاعبون", color = Color.White)

        playersNames.forEachIndexed { index, name ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedTextField(
                    value = name,
                    onValueChange = { newValue ->
                        playersNames = playersNames.toMutableList().also {
                            it[index] = newValue
                        }
                    },
                    label = { Text("اللاعب ${index + 1}", color = Color.White) },
                    modifier = Modifier.weight(1f)
                )

                if (playersNames.size > 4) {
                    Spacer(Modifier.width(8.dp))
                    TextButton(
                        onClick = {
                            playersNames = playersNames.toMutableList().also {
                                it.removeAt(index)
                            }
                        }
                    ) {
                        Text("❌ إزالة لاعب", color = Color.White)
                    }
                }
            }

            Spacer(Modifier.height(10.dp))
        }

        TextButton(onClick = { playersNames = playersNames + "" }) {
            Text("➕ إضافة لاعب", color = Color.White)
        }

        Spacer(Modifier.height(20.dp))

        Text("👿 عدد المافيوسو", fontWeight = FontWeight.Medium, fontSize = 16.sp, color = Color.White)
        Spacer(Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = murdererCount == 1,
                    onClick = { murdererCount = 1 },
                    colors = RadioButtonDefaults.colors(Color.White)
                )
                Text("1", color = Color.White)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = murdererCount == 2,
                    onClick = { murdererCount = 2 },
                    colors = RadioButtonDefaults.colors(Color.White)
                )
                Text("2", color = Color.White)
            }
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {
                viewModel.clearPlayers()
                viewModel.setPlayers(caseData.players)
                viewModel.setStory(caseData.story)
                navController.navigate(Screen.Game.route)
            },
            enabled = isValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ابدأ التحقيق 🔥", fontSize = 18.sp, color = Color.White)
        }
    }
}