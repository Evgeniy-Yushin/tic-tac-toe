fun main() {
    val game = AllGame()

    game.startGame()

    while (true) {
        if (game.selection == 'X') game.selection = 'O' else game.selection = 'X'
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
    }

    private fun winCheck(size: Int) : Boolean {
        val check: Array<Int> = Array(4, {0;0;0;0})

        for (i in 0 until size) {
            for (j in 0 until size) {
                if (field[i][j] == selection) {
                    check[0] += 1
                }
            }
            if (check[0] == size) return true else check[0] = 0
        }

        for (i in 0 until size) {
            for (j in 0 until size) {
                if (field[j][i] == selection) {
                    check[1] += 1
                }
            }
            if (check[1] == size) return true else check[1] = 0
        }

        for (i in 0 until size) {
            if (field[i][i] == selection) check[2] += 1
        }

        for (i in 0 until size) {
            if (field[i][size - 1 - i] == selection) check[3] += 1
        }
        if (check[0] == size || check[1] == size || check[2] == size || check[3] == size) return true
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

