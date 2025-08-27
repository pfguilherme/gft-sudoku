package io.github.pfguilherme.sudoku.ui.frame;

import io.github.pfguilherme.sudoku.ui.panel.BoardPanel;

import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame
{
    private JPanel boardPanel;

    public BoardFrame(Dimension dimension, final int boardSize)
    {
        this.boardPanel = new BoardPanel(dimension, boardSize, boardSize);
    }
}
