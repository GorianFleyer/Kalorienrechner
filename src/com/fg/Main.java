package com.fg;

import com.con.Connect;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here


        Scanner scanner = new Scanner(System.in);
     /*   b("Please insert grams and ratio");
        b(Calculator.Sum(scanner.nextDouble(), scanner.nextDouble()) + "");
        b("Please insert Sum and ratio");
        b(Calculator.Gram(scanner.nextDouble(), scanner.nextDouble()) + "");
        b("Please insert gram and sum");
        b(Calculator.Ratio(scanner.nextDouble(), scanner.nextDouble()) + "");*/
        b("Alt: 400 neu 200  ergibt "+ Calculator.AddToDailyCal(400, 200));
        scanner.close();

        Connect connect = new Connect();
        connect.connect();

        b(Calculator.CalorieRequired(90, 178,1,29,0.95) + "");
    }

    // Faulheit
    public static void b(String s)
    {
        System.out.println( s);
    }
}
