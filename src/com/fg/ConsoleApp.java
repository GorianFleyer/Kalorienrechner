package com.fg;

import com.con.*;
import com.functions.Base;
import com.functions.ProfileCalculator;
import com.functions.TupperCalc;

import java.sql.Connection;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;

public class ConsoleApp {
    public ConsoleApp(LocalDate localDate) {
        Connect connect = new Connect();
        Base base = new Base(0,localDate,connect);
        int scan = 0;
        Scanner scanner = new Scanner(System.in);

        List<double[]> tupper = Select.SelectFromTupper(connect.connect());
        for (double[] i:tupper) {
            System.out.println("Tupper mit " + i[0] + "Kalorien auf " + i[1] + "g enthält noch " + i[2] + " Gramm" );
            System.out.println("Das sind " + TupperCalc.CalcTupper(i[0],i[1],i[2]) + " Kalorien");

        }
        LinkedHashMap<String, double[]>  profile = Select.SelectFromprofile(connect.connect());
        for (String i : profile.keySet()) {
            System.out.println("Eingetragenes Profil: " + i + " Größe: " + profile.get(i)[0]);
            System.out.println("Pal-Wert: " +profile.get(i)[1] + "\nAlter: " + profile.get(i)[2]);

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
            System.out.println("10. Account anlegen");
            System.out.println("11. Aufstellung Kalorien");
            System.out.println("12. Tupper essen");
            System.out.println();
            System.out.println("0. Ende");

            try {
                scan = scanner.nextInt();

                switch (scan) {

                    case 1:
                        base.ListCalories();
                        break;
                    case 2:
                        base.ListWeight();
                        break;
                    case 3:
                        base.BMIBerechnen();
                        break;
                    case 4:
                        base.CalcCalorie();
                        break;
                    case 5:
                        base.UpdateWeight();
                        break;
                    case 6:
                        base.UpdateCalories();
                        break;
                    case 7:
                        base.CalculateCalories();
                        break;
                    case 8:
                        base.Tuppern();
                        break;
                    case 9:
                        base.Beer();
                        break;
                    case 10:
                        base.CreateAccount();
                        break;
                    case 11:
                        base.DifferenceCalories();
                        break;
                    case 12:
                        base.EatTupper();
                }
            } catch (Exception e) {
                System.out.println("Falsche Eingabe");
            }


        } while (scan != 0);
    }

}
