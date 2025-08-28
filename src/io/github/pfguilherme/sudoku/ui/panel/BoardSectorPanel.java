package io.github.pfguilherme.sudoku.ui.panel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BoardSectorPanel extends JPanel
{
    private static final Color LINE_BORDER_COLOR = new Color(0, 0, 0, 0);

    public BoardSectorPanel(final int boardSectorSize)
    {
        var layout = new GridLayout(boardSectorSize, boardSectorSize);
        this.setLayout(layout);

        this.setBorder(new LineBorder(LINE_BORDER_COLOR));
    }
}
