package com.Panel;

import com.connection.Connect;
import com.functions.DateCalc;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class MainFrame extends JFrame
{
    int localDate;
    public MainFrame(LocalDate localDateDate)
    {
        this.localDate = DateCalc.toInt(localDateDate);
        Connect connect = new Connect();
        // Fenster
        setTitle("Kalorienrechner");
        setSize(600,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        Container pane = getContentPane();

        //Panels
        setJMenuBar(new Menu(localDate, connect));
        add(new WeightBar(localDate,connect));
        add(new RecipeBar(localDate,connect));
        add(new BeerPanel(localDate,connect));

    }
}
