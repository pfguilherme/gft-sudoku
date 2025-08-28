package io.github.pfguilherme.sudoku.ui.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EvaluateGameStateButton extends JButton
{
    public EvaluateGameStateButton(ActionListener actionListener)
    {
        this.setText("Avaliar Estado de Jogo");
        this.addActionListener(actionListener);
    }
}
