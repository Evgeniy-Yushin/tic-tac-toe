package tictactoe

import java.awt.Color
import java.awt.TextField
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.*
import javax.swing.*


class StartPage : JFrame(), ActionListener{
    var size = 3
    val jLabel = JLabel()
    val jButton = JButton("Ок.")
    val jSlider = JSlider()
    var selection = true
    val jLabel2 = JLabel("Ход игрока ${if (selection) "X" else "O"}")
    val field = Array(10) {Array(10) {JButton(ImageIcon("startbutton.png"))} }

    fun createUI() {
        this.setSize(516, 559)

        jLabel.setBounds(168, 180, 100, 18)
        jLabel.text ="Размер поля: "
        this.add(jLabel, null)

        jButton.setBounds(173, 300, 70, 30)
        this.add(jButton, null)

        jSlider.minimum = 3
        jSlider.maximum = 10
        jSlider.setBounds(0, 200, 400, 50)
        jSlider.majorTickSpacing = 1
        jSlider.paintTicks = true
        jSlider.snapToTicks = true
        jSlider.paintLabels = true
        this.add(jSlider, null)

        this.contentPane.layout = null
        //this.add(getJTextField(), null)
        this.title = "Крестики Нолики"

        jButton.addActionListener(this)
    }



    override fun actionPerformed(e: ActionEvent?) {
        if (e!!.source == jButton) {
            size = jSlider.value
            jLabel.text = "Размер поля: ${size}x${size}"
            jLabel.setBounds(0, 0, 200, 20)
            remove(jSlider)
            remove(jButton)

            for (i in 0 until size) {
                for (j in 0 until size) {
                    field[i][j].setBounds(50 * j, 50 * i + 20, 50, 50)
                    add(field[i][j])
                    field[i][j].isVisible = true
                    field[i][j].addActionListener(this)
                }
            }
            jLabel2.setBounds(423, 0, 116, 20)
            jLabel2.isVisible = true
            add(jLabel2)
            repaint()
            return
        }

        for (i in 0 until size) {
            for(j in 0 until size) {
                if (e.source == field[i][j]) {
                    field[i][j].text = if (selection) "X" else "O"
                    if (selection) field[i][j].background = Color.GREEN else field[i][j].background = Color.RED
                    field[i][j].isEnabled = false
                    selection = !selection
                    jLabel2.text = "Ход игрока ${if (selection) "X" else "O"}"

                    if (endgame()) this.isEnabled = false
                }
            }
        }
        repaint()
    }

    fun draw() : Boolean {
        for (i in 0 until size) for (j in 0 until size) if (field[i][j].isEnabled) return false
        return true
    }

    fun winCheck(size: Int) : Boolean {
        val check: Array<Int> = Array(4) {0;0;0;0}

        for (i in 0 until size) {
            for (j in 0 until size) {
                if (field[i][j].background == (if (selection) Color.RED else Color.GREEN)) {
                    check[0] += 1
                }
            }
            if (check[0] == size) return true else check[0] = 0
        }

        for (i in 0 until size) {
            for (j in 0 until size) {
                if (field[j][i] == (if (selection) Color.RED else Color.GREEN)) {
                    check[1] += 1
                }
            }
            if (check[1] == size) return true else check[1] = 0
        }

        for (i in 0 until size) {
            if (field[i][i] == (if (selection) Color.RED else Color.GREEN)) check[2] += 1
        }

        for (i in 0 until size) {
            if (field[i][size - 1 - i] == (if (selection) Color.RED else Color.GREEN)) check[3] += 1
        }
        if (check[0] == size || check[1] == size || check[2] == size || check[3] == size) return true
        return false
    }

    fun endgame() : Boolean {
        if (winCheck(size)) {
            jLabel.text = ("Игрок ${if (selection) 'O' else 'X'} победил. Игра окончена.")
            return true
        }
        if (draw()) {
            jLabel.text = "Ничья. Игра окончена."
            return true
        }
        else return false
    }

   /* inner class FieldButtonActionListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            finalfield[i].addActionListener(finalgameal[i]).run {
                finalfield[i].text = if (selection) "X" else "O"
                selection = !selection
                repaint()
            }
        }
    }*/
}