package tictactoe


import java.awt.TextField
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*


class StartPage : JFrame() {
    var size = 3
    val jLabel = JLabel()
    val jButton = JButton("Ок.")
    val jSlider = JSlider()
    val field: Array<Array<JButton>> = arrayOf()

    fun createUI() {
        this.setSize(800, 900)

        jLabel.setBounds(195, 180, 100, 18)
        jLabel.text ="Размер поля: "
        this.add(jLabel, null)

        jButton.setBounds(210, 300, 70, 30)
        this.add(jButton, null)

        jSlider.minimum = 3
        jSlider.maximum = 10
        jSlider.setBounds(0, 200, 485, 50)
        jSlider.majorTickSpacing = 1
        jSlider.paintTicks = true
        jSlider.snapToTicks = true
        jSlider.paintLabels = true
        this.add(jSlider, null)

        this.contentPane.setLayout(null)
        //this.add(getJTextField(), null)
        this.title = "Крестики Нолики"

        val al = BActionListener()
        jButton.addActionListener(al)
    }

    inner class BActionListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            size = jSlider.value
            remove(jSlider)
            remove(jButton)
            jLabel.setBounds(350, 18, 100, 18)
            jLabel.text = "Размер поля = $size"

            for (i in 0 until size) {
                for (j in 0 until size) {
                    field[i][j] = JButton("*")
                    field[i][j].setBounds(j*40, i* 40, 40, 40)
                    add(field[i][j])
                    field[i][j].isVisible = true
                }
            }
            repaint()
        }
    }
}