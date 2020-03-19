package com.fg;

import com.con.Connect;
import com.con.Insert;
import com.con.Select;
import com.con.Update;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleApp {
    public ConsoleApp(LocalDate localDate)
    {
        int scan = 0;
        Scanner scanner = new Scanner(System.in);
        Connect connect = new Connect();

        do
            {
                System.out.println("Kalorienrechner Hauptmenue");
                System.out.println("##########################");
                System.out.println("1. Anzeigen der Kalorien der letzten Tage");
                System.out.println("2. Anzeigen des Gewichts der letzten Tage");
                System.out.println("3. Berechnen des BMI");
                System.out.println("4. Berechnen des Kalorienverbrauchs");
                System.out.println("5. Tagesgewicht eintragen");
                System.out.println("6. Kalorien für den Tag eintragen");
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
                            System.out.println("Nicht Implementiert");
                            break;
                        case 4:
                            System.out.println("Nicht Implementiert");
                            break;
                        case 5:
                            UpdateWeight(localDate,connect);
                            break;
                        case 6:
                           UpdateCalories(localDate,connect);
                            break;
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Falsche Eingabe");
                }


        }while(scan !=0);
    }

    public static void UpdateWeight(LocalDate localDate, Connect connect)
    {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wie viel Kilogramm haben Sie heute auf der Waage gesehen?");
        System.out.print("Gewicht: ");
        input = scanner.next();
        try
        {
            if(!Select.SelectFromDayWeight(connect.connect()).containsKey(localDate.toString()))
            {
                Insert.insertDayWeight(connect.connect(),localDate.toString(),Double.parseDouble(input));
            }
            else
            {
                System.out.println("gibt's schon");
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    public static void UpdateCalories(LocalDate localDate, Connect connect)
    {
        String input = "";
        String choice = "y";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wie viel Kalorien haben Sie gegessen?");
        System.out.print("Kalorien: ");
        input = scanner.next();
        try
        {
            if(!Select.SelectFromCaloriesOnDay(connect.connect()).containsKey(localDate.toString()))
            {
                Insert.insertCaloriesOnDay(connect.connect(),localDate.toString(),Double.parseDouble(input));
            }
            else
            {

                System.out.print("Kalorien hinzufügen[y/n default: y]?");
                choice = scanner.next();
                if(choice.equals("y") || choice.equals(""))
                {
                    Update.UpdateCaloriesOnDay(connect.connect(),localDate.toString(),Double.parseDouble(input));
                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
