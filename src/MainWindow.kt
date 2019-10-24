package tictactoe

import java.awt.Color
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener


class MainWindow : JFrame(), ActionListener, ChangeListener {
    var size = 3
    var rule = 3
    var selection = true
    private val jLabel = JLabel("Размер поля: ")
    private val jButton = JButton("Ок.")
    private val jSlider = JSlider()
    private val jSlider2 = JSlider()
    private val jLabel2 = JLabel("Условие выигрыша: ")
    private val field = Array(10) {Array(10) {JButton()} }

    fun createUI() {
        this.setSize(516, 559)

        jLabel.setBounds(210, 180, 100, 18)
        this.add(jLabel, null)

        jLabel2.setBounds(195, 380, 130, 18)
        jLabel2.isVisible = true
        this.add(jLabel2, null)

        jButton.setBounds(216, 480, 70, 30)
        this.add(jButton, null)

        jSlider.minimum = 3
        jSlider.maximum = 10
        jSlider.setBounds(0, 200, 500, 50)
        jSlider.majorTickSpacing = 1
        jSlider.paintTicks = true
        jSlider.snapToTicks = true
        jSlider.paintLabels = true
        this.add(jSlider, null)

        jSlider2.minimum = 3
        jSlider2.maximum = 10
        jSlider2.setBounds(0, 400, 500, 50)
        jSlider2.majorTickSpacing = 1
        jSlider2.paintTicks = true
        jSlider2.snapToTicks = true
        jSlider2.paintLabels = true
        this.add(jSlider2, null)

        this.contentPane.layout = null
        this.title = "Крестики Нолики"

        jButton.addActionListener(this)
        jSlider.addChangeListener(this)
    }

    override fun stateChanged(e: ChangeEvent?) {
        jSlider2.maximum = jSlider.value
    }

    override fun actionPerformed(e: ActionEvent?) {
        if (e!!.source == jButton) {
            size = jSlider.value
            rule = jSlider2.value
            jLabel.text = "Размер поля: ${size}x${size}                                Условие выигрыша: $rule"
            jLabel2.text = "Ход игрока ${if (selection) "X" else "O"}"
            jLabel.setBounds(0, 0, 500, 20)
            remove(jSlider)
            remove(jButton)
            remove(jSlider2)

            for (i in 0 until size) {
                for (j in 0 until size) {
                    field[i][j].setBounds(50 * j, 50 * i + 20, 50, 50)
                    add(field[i][j])
                    field[i][j].isVisible = true
                    field[i][j].addActionListener(this)
                }
            }
            jLabel2.setBounds(423, 0, 116, 20)
            repaint()
            return
        }

        for (i in 0 until size) {
            for(j in 0 until size) {
                if (e.source == field[i][j]) {
                    field[i][j].text = if (selection) "X" else "O"
                    if (selection) field[i][j].background = Color.GREEN else field[i][j].background = Color.RED
                    field[i][j].isEnabled = false
                    jLabel2.text = "Ход игрока ${if (selection) "O" else "X"}"
                    if (endgame()) this.isEnabled = false
                    selection = !selection
                }
            }
        }
        repaint()
    }

    private fun draw() : Boolean {
        for (i in 0 until size) for (j in 0 until size) if (field[i][j].isEnabled) return false
        return true
    }

    private fun winCheck() : Boolean {
        for (i in 0 until size - rule + 1) {
            for (j in 0 until size - rule + 1) {
                if (digCheck(i, j) || lanesCheck(i, j)) return true
            }
        }
        return false
    }

    private fun endgame() : Boolean {
        if (winCheck()) {
            jLabel.text = ("Игрок ${if (selection) 'X' else 'O'} победил. Игра окончена.")
            return true
        }
        if (draw()) {
            jLabel.text = "Ничья. Игра окончена."
            return true
        }
        return false
    }


    private fun digCheck(d1: Int, d2: Int) : Boolean {
        var toright = true
        var toleft = true
        for (i in 0 until rule) {
                toright = toright && (field[i + d1][i + d2].background == (if (selection) Color.GREEN else Color.RED))
                toleft = toleft && (field[rule - i - 1 + d1][i + d2].background == (if (selection) Color.GREEN else Color.RED))
        }
        if (toright || toleft) return true
        return false
    }

    private fun lanesCheck(d1: Int, d2: Int) : Boolean {
        var hor: Boolean
        var ver: Boolean
        for (i in d1 until rule + d1) {
            hor = true
            ver = true
            for (j in d2 until rule + d2) {
                hor = hor && (field[i][j].background == (if (selection) Color.GREEN else Color.RED))
                ver = ver && (field[j][i].background == (if (selection) Color.GREEN else Color.RED))
            }
            if (hor || ver) return true
        }
        return false
    }
}