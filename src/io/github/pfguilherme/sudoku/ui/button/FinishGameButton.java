package io.github.pfguilherme.sudoku.ui.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FinishGameButton extends JButton
{
    public FinishGameButton(ActionListener actionListener)
    {
        this.setText("Finalizar Jogo");
        this.addActionListener(actionListener);
    }
}
