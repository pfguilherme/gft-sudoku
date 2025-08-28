package io.github.pfguilherme.sudoku.ui.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NumberSelectorButton extends JButton
{
    private int number;

    public NumberSelectorButton(int number, ActionListener actionListener)
    {
        this.number = number;

        this.setText(String.valueOf(number));

        var dimension = new Dimension(50, 50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);

        this.setHorizontalAlignment(CENTER);

        this.addActionListener(actionListener);
    }

    public int getNumber()
    {
        return number;
    }
}
