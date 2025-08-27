package io.github.pfguilherme.sudoku.ui.panel;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel
{
    public BoardPanel(Dimension dimension, final int rowCount, final int columnCount)
    {
        this.setVisible(true);

        // acho que é alguma coisa com o parente dele, ou seja, o mainframe
//        var dimension = new Dimension(500, 500);
        this.setSize(dimension);
        this.setPreferredSize(dimension);

        var layout = new GridLayout(rowCount, columnCount);
        this.setLayout(layout);
    }
}
