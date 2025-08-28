package io.github.pfguilherme.sudoku.ui.screen;

import io.github.pfguilherme.sudoku.service.BoardService;
import io.github.pfguilherme.sudoku.service.EventListener;
import io.github.pfguilherme.sudoku.service.EventType;
import io.github.pfguilherme.sudoku.service.NotifierService;
import io.github.pfguilherme.sudoku.ui.button.*;
import io.github.pfguilherme.sudoku.ui.frame.MainFrame;
import io.github.pfguilherme.sudoku.ui.panel.BoardPanel;
import io.github.pfguilherme.sudoku.ui.panel.BoardSectorPanel;
import io.github.pfguilherme.sudoku.ui.panel.GameStateButtonsPanel;
import io.github.pfguilherme.sudoku.ui.panel.NumberSelectorsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MainScreen
{
    private static final Dimension MAIN_FRAME_DIMENSION = new Dimension(800, 800);
    private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(500, 500);
    private static final Dimension NUMBER_SELECTORS_PANEL_DIMENSION = new Dimension(500, 50);
    private static final Dimension GAME_STATE_BUTTONS_PANEL_DIMENSION = new Dimension(500, 50);

    private final BoardService boardService;
    private final NotifierService notifierService;

    private Integer selectedNumber;

    public MainScreen(BoardService boardService, NotifierService notifierService)
    {
        this.boardService = boardService;
        this.notifierService = notifierService;
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

        JPanel numberSelectorsPanel = new NumberSelectorsPanel(NUMBER_SELECTORS_PANEL_DIMENSION);
        JPanel gameStateButtonsPanel = new GameStateButtonsPanel(GAME_STATE_BUTTONS_PANEL_DIMENSION);

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

    private void addBoardSectorPanel(final JPanel panel, final int startRow, final int startColumn)
    {
        int boardSectorSize = BoardService.BOARD_SECTOR_SIZE;

        JPanel boardSectorPanel = new BoardSectorPanel(boardSectorSize);
        for (int row = startRow; row < boardSectorSize + startRow; row++)
        {
            for (int column = startColumn; column < boardSectorSize + startColumn; column++)
            {
                addCellButton(boardSectorPanel, row, column);
            }
        }

        panel.add(boardSectorPanel);
    }

    private void addCellButton(final JPanel panel, final int row, final int column)
    {
        var cell = boardService.getCells().get(column).get(row);
        JButton cellButton = new CellButton(cell, e -> {
            var instance = (CellButton) e.getSource();
            if (instance.getCell().isFixed())
                return;

            instance.setDisplayNumber(selectedNumber);
            instance.getCell().setActualValue(selectedNumber);
        });

        notifierService.subscribe(EventType.CLEAR_CELL, (EventListener) cellButton);
        panel.add(cellButton);
    }

    private void addNumberSelectorButton(final JPanel panel, final int number)
    {
        JButton numberSelectorButton = new NumberSelectorButton(number, e -> {
            var instance = (NumberSelectorButton) e.getSource();

            Arrays.stream(panel.getComponents()).forEach(component -> {
                var other = ((NumberSelectorButton) component);
                other.setDown(false);
                other.revalidate();
                other.repaint();
            });


            if (selectedNumber != null && selectedNumber == instance.getNumber())
            {
                instance.setDown(false);
                selectedNumber = null;
            }
            else
            {
                instance.setDown(true);
                selectedNumber = instance.getNumber();
            }
        });

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
                JOptionPane.showMessageDialog(
                    null,
                    "Há algum erro, ou o jogo não foi concluído. Tente novamente"
                );
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
                notifierService.notify(EventType.CLEAR_CELL);
            }
        });

        panel.add(resetGameButton);
    }
}
