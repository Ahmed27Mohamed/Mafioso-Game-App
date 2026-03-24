package com.a2004256_ahmedmohamed.mafiosogame.ux_ui

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import com.a2004256_ahmedmohamed.mafiosogame.model.GameState
import com.a2004256_ahmedmohamed.mafiosogame.model.Player
import com.a2004256_ahmedmohamed.mafiosogame.model.Role
import com.a2004256_ahmedmohamed.mafiosogame.ui.theme.MafiosoGameTheme

@Composable
fun VotingScreen(
    gameState: GameState,
    onPlayerEliminated: (Player) -> Unit
) {
    var selectedPlayer by remember { mutableStateOf<Player?>(null) }
    val context = LocalContext.current
    val click = remember {
        MediaPlayer.create(context, R.raw.click)
    }
    val scary = remember {
        MediaPlayer.create(context, R.raw.scary)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)
        .navigationBarsPadding()
        .statusBarsPadding()
    ) {
        scary.start()

        Text("🗳️ التصويت لإقصاء لاعب", color = Color.White, fontSize = 24.sp)
        Spacer(Modifier.height(16.dp))

        gameState.players.filter { !it.isEliminated }.forEach { player ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedPlayer = player }
                    .padding(8.dp)
                    .background(
                        if (selectedPlayer == player) Color.LightGray else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text(player.name, fontSize = 20.sp, modifier = Modifier.padding(8.dp), color = Color.White)
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            enabled = selectedPlayer != null,
            onClick = {
                selectedPlayer?.let {
                    onPlayerEliminated(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("تأكيد التصويت ✅", color = Color.White)
        }
    }
}