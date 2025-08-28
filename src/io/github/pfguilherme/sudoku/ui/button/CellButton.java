package io.github.pfguilherme.sudoku.ui.button;

import io.github.pfguilherme.sudoku.model.Cell;
import io.github.pfguilherme.sudoku.service.BoardService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CellButton extends JButton
{
    private final Cell cell;
    private Integer displayNumber;

    public CellButton(Cell cell, ActionListener actionListener)
    {
        this.cell = cell;
        this.setBackground(Color.WHITE);

        if (cell.isFixed())
        {
            this.setText(cell.getActualValue().toString());
            this.setToolTipText("Célula Fixa");
        }

        this.addActionListener(actionListener);
    }

    public Cell getCell()
    {
        return cell;
    }

    public void setDisplayNumber(Integer displayNumber)
    {
        this.displayNumber = displayNumber;
        if (displayNumber != null && displayNumber >= BoardService.CELL_MIN_ACTUAL_VALUE
            && displayNumber <= BoardService.CELL_MAX_ACTUAL_VALUE)
            this.setText(String.valueOf(displayNumber));
        else
            this.setText("");
    }
}
