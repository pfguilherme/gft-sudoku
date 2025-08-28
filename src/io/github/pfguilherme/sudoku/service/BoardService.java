package io.github.pfguilherme.sudoku.service;

import io.github.pfguilherme.sudoku.model.Board;
import io.github.pfguilherme.sudoku.model.Cell;
import io.github.pfguilherme.sudoku.model.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardService
{
    public static final int BOARD_SIZE = 9;
    public static final int BOARD_SECTOR_SIZE = 3;

    public static final int CELL_MIN_ACTUAL_VALUE = 1;
    public static final int CELL_MAX_ACTUAL_VALUE = 9;

    private final Board board;

    public BoardService(final Map<String, String> config)
    {
        this.board = new Board(createCellsOfMap(config));
    }

    public List<List<Cell>> getCells()
    {
        return board.getCells();
    }

    public void reset()
    {
        board.reset();
    }

    public boolean hasErrors()
    {
        return board.hasErrors();
    }

    public GameState getState()
    {
        return board.getState();
    }

    public boolean isGameFinished()
    {
        return board.isGameFinished();
    }

    private List<List<Cell>> createCellsOfMap(Map<String, String> config)
    {
        List<List<Cell>> cells = new ArrayList<>();
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            cells.add(new ArrayList<>());
            for (int column = 0; column < BOARD_SIZE; column++)
            {
                var cellConfig = config.get(("%s,%s").formatted(row, column));

                var isFixed = Boolean.parseBoolean(cellConfig.split(",")[1]);
                var correctValue = Integer.parseInt(cellConfig.split(",")[0]);

                var cell = new Cell(isFixed, correctValue);
                cells.get(row).add(cell);
            }
        }

        return cells;
    }
}
