package io.github.pfguilherme.sudoku.ui.panel;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel
{
    public BoardPanel(Dimension dimension, final int rowCount, final int columnCount)
    {
        this.setVisible(true);

        this.setSize(dimension);
        this.setPreferredSize(dimension);

        var layout = new GridLayout(rowCount, columnCount);
        this.setLayout(layout);
    }
}
