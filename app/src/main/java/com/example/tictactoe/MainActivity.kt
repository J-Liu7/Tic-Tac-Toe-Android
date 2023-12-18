package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.ui.theme.TicTacToeTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeGame()
        }
    }
}

@Composable
fun GameScreen(gameViewModel: GameViewModel) {
    val gameState = gameViewModel.state
    val gameEnded = gameViewModel.gameEnded
    val winner = gameViewModel.winner

    GameBoard(state = gameState, onCellClicked = gameViewModel::onCellClicked)

    if (gameEnded) {
        if (winner != null) {
            Text("Player $winner wins!")
        } else {
            Text("It's a draw!")
        }
    }

    Button(onClick = gameViewModel::onResetClicked) {
        Text("Restart Game")
    }
}

@Composable
fun GameBoard(state: GameState, onCellClicked: (Int, Int) -> Unit) {
    Column {
        // Create a 3x3 grid of buttons
        for (row in 0 until 3) {
            Row {
                for (col in 0 until 3) {
                    GameCell(cellValue = state.board[row][col]) {
                        onCellClicked(row, col)
                    }
                }
            }
        }
    }
}

@Composable
fun GameCell(cellValue: String, onCellClicked: () -> Unit) {
    Button(onClick = onCellClicked) {
        Text(text = cellValue)
    }
}
@Composable
fun TicTacToeGame() {
    val gameViewModel: GameViewModel = viewModel()
    val gameEnded = gameViewModel.gameEnded
    val winner = gameViewModel.winner
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (gameEnded) {
            if (winner != null) {
                Text("Player $winner wins!", style = MaterialTheme.typography.bodyMedium)
            } else {
                Text("It's a draw!", style = MaterialTheme.typography.bodyMedium)
            }
        }
        // Spacing between the text and the game board
        Spacer(modifier = Modifier.height(16.dp))
        // This will create a 3x3 grid for the Tic Tac Toe board
        for (row in 0 until 3) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (col in 0 until 3) {
                    Button(
                        onClick = { gameViewModel.onCellClicked(row, col) },
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f) // Makes the button square
                    ) {
                        Text(text = gameViewModel.state.board[row][col])
                    }
                }
            }
        }

        // Restart Game Button
        Button(
            onClick = { gameViewModel.onResetClicked() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Restart Game")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToeTheme {
    }
}