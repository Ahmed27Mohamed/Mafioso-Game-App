package com.a2004256_ahmedmohamed.mafiosogame.ux_ui

import android.media.MediaPlayer
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.a2004256_ahmedmohamed.mafiosogame.generate.assignRoles
import com.a2004256_ahmedmohamed.mafiosogame.model.GameState
import com.a2004256_ahmedmohamed.mafiosogame.model.Player
import com.a2004256_ahmedmohamed.mafiosogame.model.Role
import com.a2004256_ahmedmohamed.mafiosogame.ui.theme.MafiosoGameTheme
import com.a2004256_ahmedmohamed.mafiosogame.viewmodel.GameViewModel
import kotlinx.coroutines.delay

@Composable
fun GameFlowScreen(
    viewModel: GameViewModel,
    onRestartGame: () -> Unit
) {
    val players = viewModel.players
    var currentPlayerIndex by remember { mutableStateOf(0) }
    var phase by remember { mutableStateOf("role") } // role -> clues -> vote -> result -> end
    var selectedPlayer by remember { mutableStateOf<Player?>(null) }
    val context = LocalContext.current
    val click = remember {
        MediaPlayer.create(context, R.raw.click)
    }
    val win = remember {
        MediaPlayer.create(context, R.raw.win)
    }
    val lose = remember {
        MediaPlayer.create(context, R.raw.lose)
    }
    val result = remember {
        MediaPlayer.create(context, R.raw.result)
    }

    // المرحلة: توزيع الأدوار
    if (phase == "role") {
        if (currentPlayerIndex < players.size) {
            PlayerRoleScreen(
                player = players[currentPlayerIndex],
                onNext = { currentPlayerIndex++ }
            )
        } else {
            viewModel.initializeGame()
            phase = "clues"
        }
    }
    // المرحلة: عرض الأدلة + نقاش
    else if (phase == "clues") {

        val gameState = viewModel.gameState
        val gameCrime = viewModel.gameCrimeType
        if (gameState.clues.isEmpty()) {
            gameState.clues.addAll(viewModel.generateClues())
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .navigationBarsPadding()
                .statusBarsPadding()
        ) {
            Text(
                "🕵️‍♂️ نوع الجريمة: $gameCrime",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow
            )
            Spacer(Modifier.height(16.dp))
            Text("🔎 الجولة ${gameState.currentRound}", fontSize = 24.sp, color = Color.White)
            Spacer(Modifier.height(16.dp))

            CountdownTimer(totalTime = 120) {
                phase = "vote"
            }

            Spacer(Modifier.height(16.dp))

            LazyColumn {
                items(gameState.clues) { clue ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(clue.text, modifier = Modifier.padding(12.dp), color = Color.White)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                phase = "vote"
            }, modifier = Modifier.fillMaxWidth()) {
                Text("بدء التصويت 🗳️", color = Color.White)
            }
        }
    }
    // المرحلة: التصويت
    else if (phase == "vote") {
        VotingScreen(
            gameState = viewModel.gameState,
            onPlayerEliminated = { player ->
                player.isEliminated = true
                selectedPlayer = player
                phase = "result"
            }
        )
    }
    // المرحلة: إظهار نتيجة الإقصاء
    else if (phase == "result") {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val fullText = if (selectedPlayer!!.role == Role.MURDERER)
                "${selectedPlayer!!.name} مافيوسو 🔴"
            else
                "${selectedPlayer!!.name} بريء ✅"

            var visibleText by remember { mutableStateOf("") }

            LaunchedEffect(fullText) {
                visibleText = ""
                for (i in fullText.indices) {
                    visibleText += fullText[i]
                    delay(200)
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                result.start()
                Text(
                    text = visibleText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (viewModel.checkEndCondition()) {
                            phase = "end"
                        } else {
                            viewModel.nextRound()
                            phase = "clues"
                        }
                    }
                ) {
                    Text("التالي ▶️", color = Color.White)
                }
            }
        }
    }
    // المرحلة: نهاية اللعبة
    else if (phase == "end") {
        val crimeStory = viewModel.generateCrimeStory()
        val message = viewModel.getWinnerMessage()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = crimeStory,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Yellow,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(24.dp))
            Text(message, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(Modifier.height(24.dp))

            if (message == "🟢 تم القبض على المافيوسو! الأبرياء فازوا 👮‍♂️") {
                win.start()
            } else {
                lose.start()
            }

            Button(onClick = {
                click.start()
                viewModel.resetGame()
                onRestartGame()
            }) {
                Text("🔄 إعادة اللعبة", color = Color.White)
            }
        }
    }
}
