package com.functions;

import com.connection.Connect;
import com.connection.Insert;
import com.fg.Calculator;

import java.util.Scanner;

public class Tupper
{
    boolean free;
    int localDate;
    int typeOfUserInterface;
    Connect connect;
    public Tupper(boolean free, int localdate, int typeOfUserInterface, Connect connect)
    {
    this.free = free;
    this.localDate = localdate;
    this.typeOfUserInterface = typeOfUserInterface;
    this.connect = connect;
    }
    public void TupperRecipe()
    {
       Recipes recipes = new Recipes(typeOfUserInterface, localDate, connect, true);
        String rate = "";
        String gram = "";
        String choice = "y";
        double calories = 0.0;
        double counter = 0.0;
        double fullweight = 0.0;
        double tupperweight = 0.0;
        double tupperCalories = 0.0;
        Scanner scanner = new Scanner(System.in);
        if (free) {
        do {

                System.out.println("Wie viel Kalorien haben haben 100g?");
                System.out.print("Kalorien: ");
                rate = scanner.next();

                System.out.println("Wie viel haben Sie gegessen?");
                gram = scanner.next();
                try {
                    calories = Calculator.Sum(Double.parseDouble(gram), Double.parseDouble(rate));
                    System.out.println("Das sind " + calories + " Kalorien. \n In die Tupper? [y/n]");
                    choice = scanner.next();
                    if (choice.equals("y")) {
                        counter += calories;


                    }
                    System.out.println("Neue Berechnung? [y/n]");
                    choice = scanner.next();


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            while (choice.equals("y")) ;
        }
        else
        {
            if(q("Rezept tuppern?"))
            {
                counter = recipes.UseRecipe();
            }
        }

        System.out.println("Die Gesamte Mahlzeit hat " + counter + " Kalorien");
        System.out.println("Aufteilen für die Tupper?[y/]");
        if (Repetitions.choice()){

            try {
                System.out.println("Wie viel haben Sie davon gegessen?");
                fullweight = scanner.nextDouble();
                System.out.println("Wie viel haben Sie getuppert?");
                tupperweight = scanner.nextDouble();
                fullweight += tupperweight;
                tupperCalories = TupperCalc.CalcTupper(counter, fullweight, tupperweight);

                System.out.println("Heute haben Sie " + tupperCalories + " Kalorien gegessen. Auf den Tageswert?[y/n]");

                if (Repetitions.choice()){

                    Repetitions.CheckCalories(connect,localDate,tupperCalories);

                }
                System.out.println("Den Rest in die Tupper?");
                choice = scanner.next();
                if (choice.equals("y")) {

                    Insert.insertTupper(connect.connect(), localDate, counter, fullweight, tupperweight);

                }
                System.out.println("Neue Tupper? [y/n]");
                if (Repetitions.choice()){
                    TupperRecipe();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println(counter + " Kalorien gegessen auf den Tageswert?[y/n]");

            if (Repetitions.choice()) {

                Repetitions.CheckCalories(connect,localDate,counter);

            }
        }
    }
    public static void p(String s)
    {
        System.out.println(s);
    }
    public static boolean q(String s)
    {
        System.out.println(s);
        return Repetitions.choice();
    }
}
