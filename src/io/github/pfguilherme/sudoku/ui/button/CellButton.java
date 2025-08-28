package io.github.pfguilherme.sudoku.ui.button;

import io.github.pfguilherme.sudoku.model.Cell;
import io.github.pfguilherme.sudoku.service.BoardService;
import io.github.pfguilherme.sudoku.service.EventListener;
import io.github.pfguilherme.sudoku.service.EventType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CellButton extends JButton implements EventListener
{
    private static final Color FIXED_CELL_COLOR = new Color(230, 230, 230);

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
            this.setBackground(FIXED_CELL_COLOR);
        }

        this.addActionListener(actionListener);
    }

    public Cell getCell()
    {
        return cell;
    }

    public void setDisplayNumber(Integer displayNumber)
    {
        if (cell.isFixed())
            return;

        this.displayNumber = displayNumber;
        if (displayNumber != null && displayNumber >= BoardService.CELL_MIN_ACTUAL_VALUE
            && displayNumber <= BoardService.CELL_MAX_ACTUAL_VALUE)
            this.setText(String.valueOf(displayNumber));
        else
            this.setText("");
    }

    @Override
    public void update(EventType eventType)
    {
        if (eventType == EventType.CLEAR_CELL)
        {
            this.setDisplayNumber(null);
        }
    }
}
