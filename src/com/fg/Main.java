package com.fg;

import com.con.Connect;
import com.con.Insert;
import com.con.Select;

import java.sql.ResultSet;
import java.sql.SQLException;
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


        Connect connect = new Connect();

      //  Insert.insertIntoIngredient(connect.connect(),"Zwiebeln", "Onions", 40.0);

        Select.SelectFromIngridients(connect.connect());


        /*
        b(Calculator.CalorieRequired(90, 178,1,29,0.95) + "");
        b("Bitte Geben Sie Größe alter und Gewicht ein: ");
        System.out.print("Size: ");
        double size = scanner.nextDouble();
        System.out.print("Age: ");
        double age = scanner.nextDouble();
        System.out.print("Weight: ");
        double weight =scanner.nextDouble();

        if(size != 0 && weight != 0 && age != 0)
        {
            b(Calculator.BMI(size,weight) + "ist der BMI");
        }

         */
    }

    // Faulheit
    public static void b(String s)
    {
        System.out.println( s);

    }
}
