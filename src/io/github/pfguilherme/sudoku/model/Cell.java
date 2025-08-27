package io.github.pfguilherme.sudoku.model;

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
        if (isFixed)
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

    public void clear()
    {
        setActualValue(null);
    }
}
