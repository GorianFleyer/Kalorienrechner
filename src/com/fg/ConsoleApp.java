package com.fg;

import com.con.Connect;
import com.con.Insert;
import com.con.Select;
import com.con.Update;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleApp {
    public ConsoleApp(LocalDate localDate) {
        int scan = 0;
        Scanner scanner = new Scanner(System.in);
        Connect connect = new Connect();

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
            System.out.println();
            System.out.println("0. Ende");

            try {
                scan = scanner.nextInt();

                switch (scan) {

                    case 1:
                        System.out.println("Nicht Implementiert");
                        break;
                    case 2:
                        System.out.println("Nicht Implementiert");
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
                }
            } catch (Exception e) {
                System.out.println("Falsche Eingabe");
            }


        } while (scan != 0);
    }

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
            System.out.println("Der Kalorienverbrauch pro Tag liegt bei " + caloriesOnday + " Kalorien am Tag");
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
}
