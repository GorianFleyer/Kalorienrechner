package com.fg;

import com.Panel.MainFrame;

import java.awt.*;
import java.time.LocalDate;

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
