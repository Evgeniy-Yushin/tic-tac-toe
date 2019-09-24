fun main() {
    val game = AllGame()

    game.startGame()

    while (true) {
        game.playerTurn(game.selection)
        game.startGame()
        if (game.endgame()) break
    }
}

class AllGame {
    var selection = 'X'

    var size = fieldSize()

    var field = Array(size) {Array(size) {'*'} }

    private fun fieldSize() : Int{
        print("Введите размерность поля: ")
        return readLine()!!.toInt()
    }

    fun startGame() {
        for (i in 0 until size) {
            for (j in 0 until size) print(field[i][j] + " ")
            println()
        }
    }

    fun playerTurn(player: Char) {
        if (player == 'X') println("Игрок, играющий за крестик, введите координаты через пробел: ")
        else println("Игрок, играющий за нолик, введите координаты через пробел: ")
        println("X и Y от 1 до " + field.size)
        val (x, y) = readLine()!!.split(' ')
        field[x.toInt() - 1][y.toInt() - 1] = selection
        if (selection == 'X') selection = 'O' else selection = 'X'
    }

    private fun winCheck(size: Int) : Boolean{
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (field[i][j] != selection || field[i][j] != '*') break else return true
            }
        }

        for (i in 0 until size) {
            for (j in 0 until size) {
                if (field[j][i] != selection || field[j][i] != '*') break else return true
            }
        }

        for (i in 0 until size) {
                if (field[i][i] != selection || field[i][i] != '*') break else return true
        }

        for (i in 0 until size) {
                if (field[i][size - 1 - i] != selection || field[i][size - 1 - i] != '*') break else return true
        }
        return false
    }

    private fun draw() : Boolean {
        for (i in 0 until size) for (j in 0 until size) if (field[i][j] == '*') return false
        return true
    }

    fun endgame() : Boolean {
        if (winCheck(size)) {
            println("Игрок " + selection + " победил. Игра окончена.")
            return true
        }
        if (draw()) {
            println("Ничья. Игра окончена.")
            return true
        }
        else return false
    }
}

