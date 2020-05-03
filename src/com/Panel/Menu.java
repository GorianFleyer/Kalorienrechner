package com.Panel;

import com.con.Connect;
import com.functions.DateCalc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.time.LocalDate;

public class Menu extends JMenuBar {
    int localDate;
    public Menu(int localDate, Connect connect)
    {
        this.localDate = localDate;

        JMenu file = new JMenu("Datei");

        JMenu submenu = new JMenu("Submenu");
        JMenuItem item1 = new JMenuItem("Ich bin interessant");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        JMenuItem item2 = new JMenuItem("Ende");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        submenu.add(item1);
        file.add(submenu);
        file.add(item2);
        file.addSeparator();
        add(file);

    }
}
