import java.lang.reflect.Field

fun main() {
    val game = AllGame()

    game.fieldSize()
    //game.fieldInit()
    game.startGame()

    while (true) {
        game.playerTurn(game.selection)
        game.startGame()
        if (game.endgame()) break
        //if (game.winCheck(game.size) || game.draw()) break
        game.playerTurn(game.selection)
        game.startGame()
        if (game.endgame()) break
        //if (game.winCheck(game.size) || game.draw()) break
    }
}

class AllGame {
    var selection: Char = 'X'
    var size: Int = 3
    var field: Array<Array<Char>> = Array(size, {Array(size, {'*'})})
    fun fieldInit() {
        field = Array(size, {Array(size, {'*'})})
    }
    fun fieldSize() {
        print("Введите размерность поля: ")
        size = readLine()!!.toInt()
    }
    fun startGame() {
        for (i in 0..size - 1) {
            for (j in 0..size - 1) print(field[i][j] + " ")
            println()
        }
    }
    fun playerTurn(player: Char)/* : Char*/{
        if (player == 'X') println("Игрок, играющий за крестик, введите координаты через пробел: ")
        else println("Игрок, играющий за нолик, введите координаты через пробел: ")
        println("X и Y от 1 до " + field.size)
        val (x, y) = readLine()!!.split(' ')
        field[x.toInt() - 1][y.toInt() - 1] = selection
        if (selection == 'X') selection = 'O' else selection = 'X'
        //return selection
    }

    private fun winCheck(size: Int) : Boolean{
        var check = false
        for (i in 0..size - 1) {
            for (j in 0..size - 1) {
                if (field[i][j] == selection) check = true
                if (field[i][size - 1 - i] == selection) check = true
                else return false
            }
            return check
        }
        return false
    }

    private fun draw() : Boolean {
        for (i in 0..size - 1) for (j in 0..size - 1) if (field[i][j] == '*') return false
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
