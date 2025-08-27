package io.github.pfguilherme.sudoku.ui.screen;

import io.github.pfguilherme.sudoku.ui.button.CellButton;
import io.github.pfguilherme.sudoku.ui.button.NumberSelectorButton;
import io.github.pfguilherme.sudoku.ui.frame.MainFrame;
import io.github.pfguilherme.sudoku.ui.frame.NumberSelectorsPanel;
import io.github.pfguilherme.sudoku.ui.panel.BoardPanel;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class MainScreen
{
    private final static Dimension mainFrameDimension = new Dimension(800, 800);
    private final static Dimension boardPanelDimension = new Dimension(500, 500);

    private final int boardSize;

    private Integer selectedNumber;

    public MainScreen(final int boardSize)
    {
        this.boardSize = boardSize;
    }

    public void build()
    {
        JPanel boardPanel = new BoardPanel(boardPanelDimension, boardSize, boardSize);
        JPanel numberSelectorsPanel = new NumberSelectorsPanel();
        JFrame mainFrame = new MainFrame(mainFrameDimension, boardPanel, numberSelectorsPanel);

        for (int row = 0; row < boardSize; row++)
        {
            for (int column = 0; column < boardSize; column++)
            {
                // todo: obter valor da célula nos dados.
                JButton cellButton = new CellButton(row, column);
                boardPanel.add(cellButton);
//                System.out.println(boardPanel.getSize());
            }
        }

        for (int number = 1; number <= 9; number++)
        {
//            JButton numberSelectorButton = new NumberSelectorButton(number);
//            numberSelectorsPanel.add(numberSelectorButton);
            addNumberSelectorButton(numberSelectorsPanel, number);
        }

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void addCellButton()
    {
        
    }

    private void addNumberSelectorButton(JPanel panel, final int number)
    {
        JButton numberSelectorButton = new NumberSelectorButton(number, e -> {
            selectedNumber = number;
        });

        panel.add(numberSelectorButton);
    }
}
