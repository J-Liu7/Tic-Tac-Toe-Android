package com.example.tictactoe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var state by mutableStateOf(GameState())

    var gameEnded by mutableStateOf(false)
    var winner by mutableStateOf<String?>(null)

    fun onCellClicked(row: Int, col: Int) {
        if (!gameEnded && state.board[row][col].isEmpty()) {
            val newState = makeMove(state, row, col)
            state = newState

            if (checkWin(state.board)) {
                gameEnded = true
                val temp = state.currentPlayer.toString()
                winner = if (temp == "X") {
                    "O"
                } else {
                    "X"
                }
            } else if (isBoardFull(state.board)) {
                // If the board is full and there's no winner, it's a draw
                gameEnded = true
            }
        }
    }

    fun onResetClicked() {
        state = resetGame()
        gameEnded = false
        winner = null
    }

    private fun isBoardFull(board: List<List<String>>): Boolean {
        return board.all { row -> row.all { it.isNotEmpty() } }
    }
}