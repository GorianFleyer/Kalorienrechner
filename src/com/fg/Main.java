package com.fg;

import com.Panel.MainFrame;
import com.con.Connect;
import com.con.Insert;
import com.con.Select;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        LocalDate now = LocalDate.now();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame m = new MainFrame(now);
                m.setVisible(true);
            }
        });


        ConsoleApp consoleApp = new ConsoleApp(now);


    }

}
