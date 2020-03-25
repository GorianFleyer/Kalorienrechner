package com.fg;

import com.con.Connect;
import com.con.Insert;
import com.con.Select;
import com.con.Update;
import com.functions.ProfileCalculator;
import com.functions.TupperCalc;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;

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
                    case 10:
                        System.out.println("Nicht implementiert");
                        break;
                    case 11:
                        DifferenceCalories(connect,  localDate);
                        break;
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
        String profilName = "";
        double weight = 0.0;
        double size = 0.0;
        int age = 0;
        int sex = 1;
        int profileCount = 0;
        double pal = 0.0;

        LinkedList<Integer> ageList = new LinkedList<Integer>();
        LinkedList<Integer> sexList = new LinkedList<Integer>();
        LinkedList<Double> palList = new LinkedList<Double>();
        LinkedList<Double> sizeList = new LinkedList<Double>();
        LinkedList<Double> weightList = new LinkedList<Double>();
        LinkedList<String> nameList = new LinkedList<String>();
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
            LinkedHashMap<String, double[]>  profile = Select.SelectFromprofile(connect.connect());
            for (String i : profile.keySet()) {
                System.out.println("Eingetragenes Profil: " + i + " Größe: " + profile.get(i)[0]);
                System.out.println("Pal-Wert: " +profile.get(i)[1] + "\nAlter: " + profile.get(i)[2]);
                nameList.add(i);
                sizeList.add(profile.get(i)[0]);
                palList.add(profile.get(i)[1]);
                ageList.add((int)profile.get(i)[2]);
                sexList.add((int)profile.get(i)[3]);
                profileCount++;

            }
            if(profileCount == 0) {
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
                ProfileCalculator.Calc(connect,localDate,weight,size,sex,age,pal);
            }
            else
            {
                for(String i:nameList)
                {
                    int k = nameList.indexOf(i);

                    System.out.println("Für den Account " + i + " gilt:");
                    ProfileCalculator.Calc(connect,localDate,weightList.get(k),sizeList.get(k),sexList.get(k),ageList.get(k),palList.get(k));

                }
            }

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
            if(profileCount == 0) {
                System.out.println("Es wurden die Werte:\n"
                        + "Größe: " + size
                        + "\nGeschlecht: " + sex + "(Mann 1, Frau 2)"
                        + "\nAlter: " + age
                        + "\nPal-Wert: " + pal
                        + "\nEintragen[y/n]"
                );
                input = scanner.next();
                if (input.equals("y")) {
                    System.out.print("Profil Name: ");
                    profilName = scanner.next();
                    Insert.insertProfile(connect.connect(), profilName, size,pal, age,sex);

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
    public static void DifferenceCalories(Connect connect, LocalDate localDate) {
        try {
            LinkedHashMap<String, Double> calories = Select.SelectFromCaloriesOnDay(connect.connect());
            LinkedHashMap<String, Double> weight = Select.SelectFromDayWeight(connect.connect());
            LinkedHashMap<String, double[]> profile = Select.SelectFromprofile(connect.connect());

            LinkedList<String> profilList = new LinkedList<String>();
            LinkedList<String> dateList = new LinkedList<String>();
            Scanner scanner = new Scanner(System.in);
            double caloriesOnday = 0.0;
            double difference = 0.0;
            double defizitCounter=0.0;
            int profileCounter = 0;
            String input = "";
            int inputInt = 0;
            String p = "";
            double weightdifference = 0.0;

            for (String i : weight.keySet()) {
                if (!calories.containsKey(i)) {
                    System.out.println("Die Berechnung ist nur bei vollständiger Datenlage möglich. Es sind noch keine Kalorien eingetragen");
                    System.out.println("Eintragen?[y/n]");
                    input = scanner.next();
                    if (input.equals("y")) {
                        Insert.insertCaloriesOnDay(connect.connect(), localDate.toString(), 0.0);
                    } else {
                        return;
                    }
                }

                for (String k : calories.keySet()) {

                    if (!weight.containsKey(k)) {
                        System.out.println("Die Berechnung ist nur bei vollständiger Datenlage möglich");
                        return;
                    }

                }

                dateList.add(i);


            }
            for (String j : profile.keySet()) {
                System.out.println(profileCounter + ". Profil. Name: " + j);
                profilList.add(j);
                profileCounter++;

            }
            if (profileCounter == 0) {
                System.out.println("Bitte vorher ein Profil eintragen");
                return;
            }
            else
                {
                System.out.print("Bitte Profil auswählen: ");
                inputInt = scanner.nextInt();
                System.out.println("Datum\t\t\tVerbrauch\tGegessen\tDifferenz");
                for (int i = 0; i < dateList.size(); i++)
                {
                    p = dateList.get(i);
                caloriesOnday = Calculator.CalorieRequired(weight.get(p), profile.get(profilList.get(inputInt))[0],
                        (int)profile.get(profilList.get(inputInt))[1], (int)profile.get(profilList.get(inputInt))[2],
                        profile.get(profilList.get(inputInt))[3]);

                difference = caloriesOnday - calories.get(p);
                System.out.println(  p +" \t\t" + Math.round(caloriesOnday) + "\t\t" + Math.round(calories.get(p)) +"\t\t" + Math.round(difference));
                defizitCounter += difference;


            }
                weightdifference = weight.get(dateList.getFirst()) - weight.get(dateList.getLast());
                weightdifference = Math.round(weightdifference);
                    System.out.println("Kaloriendefizit im Zeitraum: " + Math.round(defizitCounter));
                    System.out.println("Das müssten:" + Calculator.calorietoFat(defizitCounter) + "kg abnahme sein");
                    System.out.println("Tatsächlicher Gewichtsverlust: " + weightdifference + "kg" );
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
