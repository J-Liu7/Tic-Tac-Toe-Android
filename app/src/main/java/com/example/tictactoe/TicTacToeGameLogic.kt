package com.example.tictactoe

class TicTacToeGameLogic {
}
fun makeMove(state: GameState, row: Int, col: Int): GameState {
    // Assume 'state' is a deep copy of your current game state
    // Check if the cell is empty
    if (state.board[row][col].isEmpty()) {
        val newBoard = state.board.mapIndexed { r, rowList ->
            if (r == row) {
                rowList.mapIndexed { c, value ->
                    if (c == col) state.currentPlayer else value
                }
            } else {
                rowList
            }
        }
        // Toggle the current player
        val newCurrentPlayer = if (state.currentPlayer == "X") "O" else "X"
        return state.copy(board = newBoard, currentPlayer = newCurrentPlayer)
    }
    return state
}

fun checkWin(board: List<List<String>>): Boolean {
    for (row in board) {
        if (row[0].isNotEmpty() && row[0] == row[1] && row[1] == row[2]) {
            return true
        }
    }

    // Check columns
    for (col in 0..2) {
        if (board[0][col].isNotEmpty() && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
            return true
        }
    }

    // Check diagonals
    if (board[0][0].isNotEmpty() && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
        return true
    }

    if (board[0][2].isNotEmpty() && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
        return true
    }

    // If none of the above conditions are met, return false
    return false
}

fun resetGame(): GameState {
    val emptyBoard = List(3) { List(3) { "" } }
    // Return a new GameState with the empty board and the starting player set to "X"
    return GameState(board = emptyBoard, currentPlayer = "X")
}
