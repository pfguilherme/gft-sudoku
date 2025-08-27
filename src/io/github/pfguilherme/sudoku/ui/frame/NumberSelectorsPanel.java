package io.github.pfguilherme.sudoku.ui.frame;

import javax.swing.*;
import java.awt.*;

// acho que poderia ser um panel na verdade
public class NumberSelectorsPanel extends JPanel
{
    public NumberSelectorsPanel()
    {
        this.setVisible(true);

        var layout = new FlowLayout();
        this.setLayout(layout);
    }
}
