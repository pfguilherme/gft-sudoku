package io.github.pfguilherme.sudoku.ui.screen;

import io.github.pfguilherme.sudoku.service.BoardService;
import io.github.pfguilherme.sudoku.ui.button.*;
import io.github.pfguilherme.sudoku.ui.frame.MainFrame;
import io.github.pfguilherme.sudoku.ui.panel.BoardPanel;
import io.github.pfguilherme.sudoku.ui.panel.BoardSectorPanel;
import io.github.pfguilherme.sudoku.ui.panel.GameStateButtonsPanel;
import io.github.pfguilherme.sudoku.ui.panel.NumberSelectorsPanel;

import javax.swing.*;
import java.awt.*;

public class MainScreen
{
    private static final Dimension MAIN_FRAME_DIMENSION = new Dimension(800, 800);
    private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(500, 500);

    private final BoardService boardService;

    private Integer selectedNumber;

    public MainScreen(BoardService boardService)
    {
        this.boardService = boardService;
    }

    public void build()
    {
        int boardSize = BoardService.BOARD_SIZE;
        int boardSectorSize = BoardService.BOARD_SECTOR_SIZE;

        JPanel boardPanel = new BoardPanel(
            BOARD_PANEL_DIMENSION,
            boardSize / boardSectorSize,
            boardSize / boardSectorSize
        );

        JPanel numberSelectorsPanel = new NumberSelectorsPanel();
        JPanel gameStateButtonsPanel = new GameStateButtonsPanel();

        JFrame mainFrame = new MainFrame(
            MAIN_FRAME_DIMENSION,
            boardPanel,
            numberSelectorsPanel,
            gameStateButtonsPanel
        );

        for (int row = 0; row < boardSize; row += boardSectorSize)
        {
            for (int column = 0; column < boardSize; column += boardSectorSize)
            {
                addBoardSectorPanel(boardPanel, row, column);
            }
        }

        for (int number = 1; number <= 9; number++)
        {
            addNumberSelectorButton(numberSelectorsPanel, number);
        }

        addFinishGameButton(gameStateButtonsPanel);
        addEvaluateGameStateButton(gameStateButtonsPanel);
        addResetButton(gameStateButtonsPanel);

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void addBoardSectorPanel(JPanel panel, final int startRow, final int startColumn)
    {
        int boardSectorSize = BoardService.BOARD_SECTOR_SIZE;

        JPanel boardSectorPanel = new BoardSectorPanel();
        for (int row = startRow; row < boardSectorSize + startRow; row++)
        {
            for (int column = startColumn; column < boardSectorSize + startColumn; column++)
            {
                addCellButton(boardSectorPanel, row, column);
            }
        }

        panel.add(boardSectorPanel);
    }

    private void addCellButton(JPanel panel, final int row, final int column)
    {
        var cell = boardService.getCells().get(row).get(column);
        JButton cellButton = new CellButton(cell, e -> {
            var instance = (CellButton) e.getSource();
            if (instance.getCell().isFixed())
                return;

            instance.setDisplayNumber(selectedNumber);
            instance.getCell().setActualValue(selectedNumber);
        });
//        cellButton.addActionListener(e -> {
//            var instance = (CellButton) e.getSource();
//            instance.setDisplayNumber(selectedNumber);
////                selectedNumber = null;
//        });

        panel.add(cellButton);
    }

    private void addNumberSelectorButton(JPanel panel, final int number)
    {
        JButton numberSelectorButton = new NumberSelectorButton(number, e -> {
            var instance = (NumberSelectorButton) e.getSource();
            if (selectedNumber != null && selectedNumber == instance.getNumber())
                selectedNumber = null;
            else
                selectedNumber = instance.getNumber();
        });
//        numberSelectorButton.addActionListener(e -> {
//            if (e.getSource() instanceof NumberSelectorButton instance)
//            {
//                if (selectedNumber == null)
//                    selectedNumber = instance.getNumber();
//                else if (selectedNumber == instance.getNumber())
//                    selectedNumber = null;
//            }
//        });

        panel.add(numberSelectorButton);
    }

    private void addFinishGameButton(final JPanel panel)
    {
        JButton finishGameButton = new FinishGameButton(e -> {
            if (boardService.isGameFinished())
            {
                JOptionPane.showMessageDialog(null, "Você finalizou o jogo com sucesso");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Há algum erro, ou o jogo não foi concluído, tente novamente");
            }
        });

        panel.add(finishGameButton);
    }

    private void addEvaluateGameStateButton(final JPanel panel)
    {
        JButton evaluateGameStateButton = new EvaluateGameStateButton(e -> {
            boolean hasErrors = boardService.hasErrors();
            var gameState = boardService.getState();

            String message = switch (gameState)
            {
                case NOT_STARTED -> "Jogo não iniciado";
                case INCOMPLETE -> "Jogo incompleto";
                case COMPLETE -> "Jogo finalizado";
            };

            message += hasErrors ? " e contém erros" : " e não contém erros";
            JOptionPane.showMessageDialog(null, message);
        });

        panel.add(evaluateGameStateButton);
    }

    private void addResetButton(final JPanel panel)
    {
        JButton resetGameButton = new ResetGameButton(e -> {
            var dialogResult = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza que deseja reiniciar o jogo?",
                "Aviso",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if (dialogResult == 0)
            {
                boardService.reset();
            }
        });

        panel.add(resetGameButton);
    }
}
