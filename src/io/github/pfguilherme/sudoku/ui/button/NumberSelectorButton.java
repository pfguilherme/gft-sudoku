package io.github.pfguilherme.sudoku.ui.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NumberSelectorButton extends JButton
{
    private static final Color DOWN_COLOR = new Color(255, 255, 0, 80);

    private final int number;
    private boolean isDown = false;

    public NumberSelectorButton(int number, ActionListener actionListener)
    {
        this.number = number;

        this.setText(String.valueOf(number));
        this.addActionListener(actionListener);
    }

    public int getNumber()
    {
        return number;
    }

    public void setDown(boolean isDown)
    {
        this.isDown = isDown;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (isDown)
        {
            g.setColor(DOWN_COLOR);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
}
