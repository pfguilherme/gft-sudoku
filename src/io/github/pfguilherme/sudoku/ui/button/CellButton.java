package io.github.pfguilherme.sudoku.ui.button;

import javax.swing.*;
import java.awt.*;

public class CellButton extends JButton
{
    private int row;
    private int column;
    private int displayNumber;

    public CellButton(final int row, final int column)
    {
        this.row = row;
        this.column = column;

        this.setBackground(Color.WHITE);
    }
}
