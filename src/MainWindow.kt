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
    val jLabel2 = JLabel("Ход игрока ${if (selection == true) "X" else "O"}")
    val field = Array(10) {Array(10) {JButton(ImageIcon("startbutton.png"))} }

    fun createUI() {
        this.setSize(416, 459)

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
            remove(jLabel)
            remove(jSlider)
            remove(jButton)

            for (i in 0 until size) {
                for (j in 0 until size) {
                    field[i][j].setBounds(40 * j, 40 * i + 20, 40, 40)
                    add(field[i][j])
                    field[i][j].isVisible = true
                    field[i][j].addActionListener(this)
                }
            }
            jLabel2.setBounds(300, 0, 116, 20)
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
                    selection = !selection
                }
            }
        }
        repaint()
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