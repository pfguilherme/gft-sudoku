package io.github.pfguilherme.sudoku.ui.frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    public MainFrame(final Dimension dimension, final JPanel boardPanel,
                     final JPanel numberSelectorsPanel, final JPanel gameStateButtonsPanel)
    {
        super("Sudoku");
        this.setVisible(true);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        var layout = new FlowLayout();
        this.setLayout(layout);

        this.add(boardPanel);
        this.add(numberSelectorsPanel);
        this.add(gameStateButtonsPanel);
    }
}
