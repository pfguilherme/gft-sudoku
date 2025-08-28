package io.github.pfguilherme.sudoku.ui.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResetGameButton extends JButton
{
    public ResetGameButton(ActionListener actionListener)
    {
        this.setText("Reiniciar Jogo");
        this.addActionListener(actionListener);
    }
}
