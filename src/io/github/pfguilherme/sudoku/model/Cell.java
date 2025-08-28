package io.github.pfguilherme.sudoku.model;

import io.github.pfguilherme.sudoku.service.BoardService;

public class Cell
{
    private final boolean isFixed;
    private Integer actualValue;
    private final int correctValue;

    public Cell(boolean isFixed, int correctValue)
    {
        this.isFixed = isFixed;
        this.correctValue = correctValue;

        if (isFixed)
        {
            this.actualValue = correctValue;
        }
    }

    public boolean isFixed()
    {
        return isFixed;
    }

    public void setActualValue(Integer actualValue)
    {
        if (isFixed || actualValue == null || actualValue < BoardService.CELL_MIN_ACTUAL_VALUE
            || actualValue > BoardService.CELL_MAX_ACTUAL_VALUE)
            return;

        this.actualValue = actualValue;
    }

    public Integer getActualValue()
    {
        return actualValue;
    }

    public int getCorrectValue()
    {
        return correctValue;
    }

    public boolean isCorrect()
    {
        return actualValue == correctValue;
    }

    public void clear()
    {
        setActualValue(null);
    }
}
