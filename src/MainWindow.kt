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
    val field = LinkedList<JButton>()

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

    fun createField() {
        remove(jSlider)
        remove(jButton)
        for (i in 0 until size) {
            for (j in 0 until size) {
                field.add(i + j, JButton())
                field[i + j].setBounds(j*40, i* 40 + 20, 40, 40)
                add(field[i + j])
                field[i + j].isVisible = true
                field[i + j].addActionListener(this)
            }
        }
        repaint()

        jLabel.setBounds(0, 0, 110, 21)
        jLabel.text = "Размер поля = $size"
        jLabel2.setBounds(324,0,110,21)
        add(jLabel2)
        jLabel2.isVisible = true
    }

    override fun actionPerformed(e: ActionEvent?) {
        if (e!!.source == jButton) size = jSlider.value
        
        for (i in 0 until size) {
            for(j in 0 until size) {
                if (e!!.source == field[i + j]) {
                    if (e.source == field[i + j])
                    field[i + j].text = if (selection) "X" else "O"
                    selection != selection
                    if (selection) field[i + j].background = Color.GREEN else field[i + j].background = Color.RED
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