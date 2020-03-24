package com.fg;

import com.con.Connect;
import com.con.Insert;
import com.con.Select;
import com.con.Update;
import com.functions.TupperCalc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    public ConsoleApp(LocalDate localDate) {
        int scan = 0;
        Scanner scanner = new Scanner(System.in);
        Connect connect = new Connect();
        List<double[]> tupper = Select.SelectFromTupper(connect.connect());
        for (double[] i:tupper) {
            System.out.println("Tupper mit " + i[0] + "Kalorien auf " + i[1] + "g enthält noch " + i[2] + " Gramm" );
            System.out.println("Das sind " + TupperCalc.CalcTupper(i[0],i[1],i[2]) + " Kalorien");

        }
        do {
            System.out.println("Kalorienrechner Hauptmenue");
            System.out.println("##########################");
            System.out.println("1. Anzeigen der Kalorien der letzten Tage");
            System.out.println("2. Anzeigen des Gewichts der letzten Tage");
            System.out.println("3. Berechnen des BMI");
            System.out.println("4. Berechnen des Kalorienverbrauchs");
            System.out.println("5. Tagesgewicht eintragen");
            System.out.println("6. Kalorien für den Tag eintragen");
            System.out.println("7. Kalorien berechnen");
            System.out.println("8. Tuppern");
            System.out.println("9. Bier");
            System.out.println();
            System.out.println("0. Ende");

            try {
                scan = scanner.nextInt();

                switch (scan) {

                    case 1:
                        ListCalories(connect);
                        break;
                    case 2:
                        ListWeight(connect);
                        break;
                    case 3:
                        BMIBerechnen(localDate, connect);
                        break;
                    case 4:
                        CalcCalorie(localDate,connect);
                        break;
                    case 5:
                        UpdateWeight(localDate, connect);
                        break;
                    case 6:
                        UpdateCalories(localDate, connect);
                        break;
                    case 7:
                        CalculateCalories(localDate, connect);
                        break;
                    case 8:
                        Tuppern(connect,localDate);
                        break;
                    case 9:
                        Beer(connect,localDate);
                }
            } catch (Exception e) {
                System.out.println("Falsche Eingabe");
            }


        } while (scan != 0);
    }
    // Set's the weight on a certain amount for a day
    public static void UpdateWeight(LocalDate localDate, Connect connect) {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wie viel Kilogramm haben Sie heute auf der Waage gesehen?");
        System.out.print("Gewicht: ");
        input = scanner.next();
        try {
            if (!Select.SelectFromDayWeight(connect.connect()).containsKey(localDate.toString())) {
                Insert.insertDayWeight(connect.connect(), localDate.toString(), Double.parseDouble(input));
            } else {
                System.out.println("gibt's schon");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    // Adds plain calories to the daily  amount
    public static void UpdateCalories(LocalDate localDate, Connect connect) {
        String input = "";
        String choice = "y";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wie viel Kalorien haben Sie gegessen?");
        System.out.print("Kalorien: ");
        input = scanner.next();
        try {
            if (!Select.SelectFromCaloriesOnDay(connect.connect()).containsKey(localDate.toString())) {
                Insert.insertCaloriesOnDay(connect.connect(), localDate.toString(), Double.parseDouble(input));
            } else {

                System.out.print("Kalorien hinzufügen[y/n default: y]?");
                choice = scanner.next();
                if (choice.equals("y") || choice.equals("")) {
                    Update.UpdateCaloriesOnDay(connect.connect(), localDate.toString(), Double.parseDouble(input));
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    // Calculates the amount of eaten calories and asks for an update to the daily calorie counter
    public static void CalculateCalories(LocalDate localDate, Connect connect) {
        String rate = "";
        String gram = "";
        String choice = "y";
        double calories = 0.0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wie viel Kalorien haben haben 100g?");
        System.out.print("Kalorien: ");
        rate = scanner.next();
        System.out.println("Wie viel haben Sie gegessen?");
        gram = scanner.next();
        try {
            calories = Calculator.Sum(Double.parseDouble(gram), Double.parseDouble(rate));
            System.out.println("Das sind " + calories + " Kalorien. \n Eintragen? [y/n]");
            choice = scanner.next();
            if (choice.equals("y")) {


                if (!Select.SelectFromCaloriesOnDay(connect.connect()).containsKey(localDate.toString())) {
                    Insert.insertCaloriesOnDay(connect.connect(), localDate.toString(), calories);
                } else {
                    Update.UpdateCaloriesOnDay(connect.connect(), localDate.toString(), calories);
                }
            }
            System.out.println("Neue Berechnung? [y/n]");
            choice = scanner.next();
            if (choice.equals("y")) {
                CalculateCalories(localDate, connect);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Asks for the weight, sex, age, size and pal and gives back the  amount of calories the body
    // needs on a normal day and how many are still edible
    public static void CalcCalorie(LocalDate localDate, Connect connect) {
        Scanner scanner = new Scanner(System.in);
        boolean weightExistedBefore = false;
        String input = "";
        double weight = 0.0;
        double size = 0.0;
        int age = 0;
        int sex = 1;
        double pal = 0.0;
        double caloriesOnday = 0.0;
        double difference = 0.0;

        try {
            if (Select.SelectFromDayWeight(connect.connect()).containsKey(localDate.toString())) {

                weight = (double) Select.SelectFromDayWeight(connect.connect()).get(localDate.toString());
                System.out.println("Der für heute eingetragene Wert ist: " + weight + "\nVerwenden?[y/n]");
                input = scanner.next();
                if (input.equals("y")) {
                    weightExistedBefore = true;
                } else {
                    System.out.print("Neuer Wert: ");
                    weight = scanner.nextDouble();
                }
            }
            else
            {
                System.out.println("Wie viel wiegen Sie?");
                weight = scanner.nextDouble();
            }
            System.out.println("Wie groß sind Sie? (in cm)");
            size = scanner.nextDouble();
            System.out.println("Sind Sie \n 1. ein Mann \n 2. eine Frau\nBitte einzelne Zahlen eingeben");
            sex = scanner.nextInt();
            System.out.println("Wie viele Jahre sind sie alt?");
            age = scanner.nextInt();
            System.out.println("Was ist ihr PALWert?");
            System.out.println("Schlafen =	0,95");
            System.out.println("Nur Sitzen oder Liegen =	1,2");
            System.out.println("Ausschließlich sitzende Tätigkeit mit wenig oder keiner körperlichen Aktivität in der Freizeit, z.B. Büroarbeit" +
                    "\n =	1,4 – 1,5");
            System.out.println("Sitzende Tätigkeit mit zeitweilig gehender oder stehender Tätigkeit, z.B. Studierende, Fließbandarbeiter, Laboranten, Kraftfahrer" +
                    "\n =	1,6 – 1,7");
            System.out.println("Überwiegend gehende oder stehende Tätigkeit, z.B. Verkäufer, Kellner, Handwerker, Mechaniker, Hausfrauen" +
                    "\n =	1,8 – 1,9");
            System.out.println("Körperlich anstrengende berufliche Arbeit =	2,0 – 2,4");
            pal = scanner.nextDouble();
            caloriesOnday = Calculator.CalorieRequired(weight,size,sex,age,pal);
            if (!Select.SelectFromCaloriesOnDay(connect.connect()).containsKey(localDate.toString())) {

                Insert.insertCaloriesOnDay(connect.connect(), localDate.toString(), 0.0);
            }

            difference = caloriesOnday - (double) Select.SelectFromCaloriesOnDay(connect.connect()).get(localDate.toString());
            System.out.println("Der Kalorienverbrauch pro Tag liegt bei " + caloriesOnday + " Kalorien am Tag");

            System.out.println("Sie können noch " + difference + " Kalorien zu sich nehmen.");
            if(!weightExistedBefore)
            {
                System.out.println("Gewicht übernehmen?[y/n]");
                input = scanner.next();
                if(input.equals("y"))
                {
                    try {
                        if (!Select.SelectFromDayWeight(connect.connect()).containsKey(localDate.toString())) {
                            Insert.insertDayWeight(connect.connect(), localDate.toString(),weight);
                        } else {
                           Update.UpdateDayWeight(connect.connect(),localDate.toString(),weight);
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // Calculates the BMI based on size and weight and wants to update it to the table
    public static void BMIBerechnen(LocalDate localDate, Connect connect) {
        Scanner scanner = new Scanner(System.in);
        boolean weightExistedBefore = false;
        String input = "";
        double weight = 0.0;
        double size = 0.0;
        int age = 0;
        int sex = 1;
        double pal = 0.0;
        double BMI = 0.0;
        try {
            if (Select.SelectFromDayWeight(connect.connect()).containsKey(localDate.toString())) {
                weight = (double)Select.SelectFromDayWeight(connect.connect()).get(localDate.toString());
                System.out.println("Der für heute eingetragene Wert ist: " + weight + "\nVerwenden?[y/n]");
                input = scanner.next();
                if (input.equals("y")) {
                    weightExistedBefore = true;
                } else {
                    System.out.print("Neuer Wert: ");
                    weight = scanner.nextDouble();
                }
            } else {
                System.out.println("Wie viel wiegen Sie?");
                weight = scanner.nextDouble();
            }
            System.out.println("Wie groß sind Sie? (in cm)");
            size = scanner.nextDouble();
            BMI = Calculator.BMI(size,weight*10000);
            System.out.println("Ihr BMI ist " + BMI);

            if (!weightExistedBefore) {
                System.out.println("Gewicht übernehmen?[y/n]");
                input = scanner.next();
                if (input.equals("y")) {
                    try {
                        if (!Select.SelectFromDayWeight(connect.connect()).containsKey(localDate.toString())) {
                            Insert.insertDayWeight(connect.connect(), localDate.toString(), weight);
                        } else {
                            Update.UpdateDayWeight(connect.connect(), localDate.toString(), weight);
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // Lists the Calorie Input on all input dates
    public static void ListCalories(Connect connect)
    {
        LinkedHashMap<String, Double> temp = Select.SelectFromCaloriesOnDay(connect.connect());
        for (String i : temp.keySet()) {
            System.out.println("Datum: " + i + " Kalorien: " + temp.get(i));
        }
    }
    // Lists the Weight on all input dates
    public static void ListWeight(Connect connect)
    {
        LinkedHashMap<String, Double> temp = Select.SelectFromDayWeight(connect.connect());
        for (String i : temp.keySet()) {
            System.out.println("Datum: " + i + " Gewicht: " + temp.get(i));
        }
    }
    public static void Tuppern(Connect connect, LocalDate localDate)
    {
        String rate = "";
        String gram = "";
        String choice = "y";
        double calories = 0.0;
        double counter = 0.0;
        double fullweight = 0.0;
        double tupperweight = 0.0;
        double tupperCalories = 0.0;
        Scanner scanner = new Scanner(System.in);
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
        while (choice.equals("y"));

        try {
            System.out.println("Wie viel haben Sie davon gegessen?");
            fullweight = scanner.nextDouble();
            System.out.println("Wie viel haben Sie getuppert?");
            tupperweight = scanner.nextDouble();
            fullweight += tupperweight;
            tupperCalories = TupperCalc.CalcTupper(counter,fullweight,tupperweight);
            System.out.println("Die Gesamte Mahlzeit hat " + counter + " Kalorien");
            System.out.println("Heute haben Sie " + tupperCalories + " Kalorien gegessen. Auf den Tageswert?[y/n]");

            choice = scanner.next();
            if (choice.equals("y")) {


                if (!Select.SelectFromCaloriesOnDay(connect.connect()).containsKey(localDate.toString())) {
                    Insert.insertCaloriesOnDay(connect.connect(), localDate.toString(), tupperCalories);
                } else {
                    Update.UpdateCaloriesOnDay(connect.connect(), localDate.toString(), tupperCalories);
                }
            }
            System.out.println("Den Rest in die Tupper?");
            choice = scanner.next();
            if (choice.equals("y")) {

                    Insert.insertTupper(connect.connect(), localDate.toString(), counter,fullweight, tupperweight);

            }
            System.out.println("Neue Tupper? [y/n]");
            choice = scanner.next();
            if (choice.equals("y")) {
                Tuppern(connect,localDate);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


    }
    public static void Beer(Connect connect, LocalDate localDate)
    {
        double beer;
        String choice ="";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wie viel Bier?");
        try{
            beer = scanner.nextDouble();
            beer = beer * 215.0;
            System.out.println(beer + " Kalorien hinzufügen?[y/n}");
            choice = scanner.next();
            if (choice.equals("y")) {


                if (!Select.SelectFromCaloriesOnDay(connect.connect()).containsKey(localDate.toString())) {
                    Insert.insertCaloriesOnDay(connect.connect(), localDate.toString(), beer);
                } else {
                    Update.UpdateCaloriesOnDay(connect.connect(), localDate.toString(), beer);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
