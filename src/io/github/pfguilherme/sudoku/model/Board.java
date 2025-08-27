package io.github.pfguilherme.sudoku.model;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board
{
    private final List<List<Cell>> cells;

    public Board(List<List<Cell>> cells)
    {
        this.cells = cells;
    }

    public List<List<Cell>> getCells()
    {
        return cells;
    }

    private boolean isAnyNonFixedCellFilled()
    {
        return cells.stream().flatMap(Collection::stream).anyMatch(
            cell -> !cell.isFixed() && nonNull(cell.getActualValue())
        );
    }

    private boolean isAnyCellNull()
    {
        return cells.stream().flatMap(Collection::stream).anyMatch(
            cell -> isNull(cell.getActualValue())
        );
    }

    private boolean isAnyCellIncorrect()
    {
        return cells.stream().flatMap(Collection::stream).anyMatch(
            cell ->
                nonNull(cell.getActualValue()) &&
                !cell.getActualValue().equals(cell.getCorrectValue())
        );
    }

    public GameState getState()
    {
        if (!isAnyNonFixedCellFilled())
        {
            return GameState.NOT_STARTED;
        }

        return isAnyCellNull() ? GameState.INCOMPLETE : GameState.COMPLETE;
    }

    public boolean hasErrors()
    {
        if (getState() == GameState.NOT_STARTED)
        {
            return false;
        }

        return isAnyCellIncorrect();
    }

    public boolean setCellValue(final int column, final int row, final int value)
    {
        var cell = cells.get(column).get(row);
        if (cell.isFixed())
        {
            return false;
        }

        cell.setActualValue(value);
        return true;
    }

    public boolean clearCellValue(final int column, final int row)
    {
        var cell = cells.get(column).get(row);
        if (cell.isFixed())
        {
            return false;
        }

        cell.clear();
        return true;
    }

    public void reset()
    {
        cells.forEach(column -> column.forEach(Cell::clear));
    }

    public boolean isGameFinished()
    {
        return !hasErrors() && getState() == GameState.COMPLETE;
    }
}
