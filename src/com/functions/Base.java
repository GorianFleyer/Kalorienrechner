package com.functions;

import com.con.*;
import com.fg.Calculator;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Base
{
    // 0 Konsole, 1, Graphic, 2 Web
    int typeOfUserInterface;
    LocalDate localDate;
    Connect connect;
    public Base(int TypeOfUserIntercace, LocalDate localDate, Connect connect)
    {
        this.localDate = localDate;
        this.connect = connect;
        this.typeOfUserInterface = typeOfUserInterface;

    }
    // Set's the weight on a certain amount for a day
    public void UpdateWeight() {
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
    public void UpdateCalories() {
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
    public void CalculateCalories() {
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
                CalculateCalories();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Asks for the weight, sex, age, size and pal and gives back the  amount of calories the body
    // needs on a normal day and how many are still edible
    public void CalcCalorie() {
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
            LinkedHashMap<String, double[]> profile = Select.SelectFromprofile(connect.connect());
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
                    ProfileCalculator.Calc(connect,localDate,weight,sizeList.get(k),sexList.get(k),ageList.get(k),palList.get(k));
                    System.in.read();

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
    public void BMIBerechnen() {
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
    public void ListCalories()
    {
        LinkedHashMap<String, Double> temp = Select.SelectFromCaloriesOnDay(connect.connect());
        for (String i : temp.keySet()) {
            System.out.println("Datum: " + i + " Kalorien: " + temp.get(i));
        }
    }
    // Lists the Weight on all input dates
    public void ListWeight()
    {
        LinkedHashMap<String, Double> temp = Select.SelectFromDayWeight(connect.connect());
        for (String i : temp.keySet()) {
            System.out.println("Datum: " + i + " Gewicht: " + temp.get(i));
        }
    }
    public void Tuppern()
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
        System.out.println("Die Gesamte Mahlzeit hat " + counter + " Kalorien");
        System.out.println("Aufteilen für die Tupper?[y/]");
        choice = scanner.next();
        if (choice.equals("y")) {

            try {
                System.out.println("Wie viel haben Sie davon gegessen?");
                fullweight = scanner.nextDouble();
                System.out.println("Wie viel haben Sie getuppert?");
                tupperweight = scanner.nextDouble();
                fullweight += tupperweight;
                tupperCalories = TupperCalc.CalcTupper(counter, fullweight, tupperweight);

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

                    Insert.insertTupper(connect.connect(), localDate.toString(), counter, fullweight, tupperweight);

                }
                System.out.println("Neue Tupper? [y/n]");
                choice = scanner.next();
                if (choice.equals("y")) {
                    Tuppern();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println(counter + " Kalorien gegessen auf den Tageswert?[y/n]");

            choice = scanner.next();
            if (choice.equals("y")) {


                if (!Select.SelectFromCaloriesOnDay(connect.connect()).containsKey(localDate.toString())) {
                    Insert.insertCaloriesOnDay(connect.connect(), localDate.toString(), counter);
                } else {
                    Update.UpdateCaloriesOnDay(connect.connect(), localDate.toString(), counter);
                }
            }
        }


    }
    public void Beer()
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
    public void DifferenceCalories() {
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
            else if(profileCounter>1) {
                System.out.print("Bitte Profil auswählen: ");
                inputInt = scanner.nextInt();
                System.out.println("Datum\t\t\tVerbrauch\tGegessen\tDifferenz\tGewicht\t\tBMI");
            }
            else {
                inputInt = 0;
            }
            for (int i = 0; i < dateList.size(); i++)
            {
                p = dateList.get(i);
                caloriesOnday = Calculator.CalorieRequired(weight.get(p), profile.get(profilList.get(inputInt))[0],
                        (int)profile.get(profilList.get(inputInt))[3], (int)profile.get(profilList.get(inputInt))[2],
                        profile.get(profilList.get(inputInt))[1]);
                // CalorieRequired(double weight, double size, int sex, int age, double pal)
                // {rs.getDouble("size"), rs.getDouble("pal"), rs.getDouble("age"),rs.getDouble("sex")}

                difference = caloriesOnday - calories.get(p);
                System.out.println(  p +" \t\t" + s(caloriesOnday) + "\t\t" + s(calories.get(p)) +"\t\t"
                        + s(difference)+"\t\t\t"+ weight.get(p)+"\t\t"+s(Calculator.BMI( profile.get(profilList.get(inputInt))[0],weight.get(p))*10000));
                defizitCounter += difference;


            }
            weightdifference = weight.get(dateList.getFirst()) - weight.get(dateList.getLast());
           // weightdifference = Math.round(weightdifference);
            System.out.println("Kaloriendefizit im Zeitraum: " + s(defizitCounter));
            System.out.println("Das müssten:" + s(Calculator.calorietoFat(defizitCounter)) + "kg abnahme sein");
            System.out.println("Tatsächlicher Gewichtsverlust: " + s(weightdifference) + "kg" );
            System.out.println("Geschätzter tatsächlicher Pal-Wert: " + s(Calculator.PAL(Calculator.FatToCalories(weightdifference),defizitCounter,profile.get(profilList.get(inputInt))[1])));
            System.in.read();
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void CreateAccount()
    {
        Scanner scanner = new Scanner(System.in);

        String input = "";
        String profilName = "";

        double size = 0.0;
        int age = 0;
        int sex = 1;

        double pal = 0.0;
        System.out.print("Profil Name: ");
        profilName = scanner.next();
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

        System.out.println("Es wurden die Werte:"
                + "\nName: " + profilName
                + "\nGröße: " + size
                + "\nGeschlecht: " + sex + "(Mann 1, Frau 2)"
                + "\nAlter: " + age
                + "\nPal-Wert: " + pal
                + "\nEintragen[y/n]"
        );
        input = scanner.next();
        if (input.equals("y")) {

            Insert.insertProfile(connect.connect(), profilName, size,pal, age,sex);

        }

    }
    public void EatTupper()
    {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        String choice = "";
        try {

            List<double[]> tupper = Select.SelectFromTupper(connect.connect());
            for (double[] i : tupper) {
                System.out.println("Tupper mit " + i[0] + "Kalorien auf " + i[1] + "g enthält noch " + i[2] + " Gramm");
                System.out.println("Das sind " + TupperCalc.CalcTupper(i[0], i[1], i[2]) + " Kalorien");
                System.out.println("ID: " + i[3]);

            }
            System.out.println("Welche Tupper möchten Sie essen?");
            input = scanner.nextInt();
            for (double[] i : tupper)
            {
                if(input == i[3])
                {
                    // @Todo Hier eine Auswahl einfügen, ob alles zu essen ist
                    System.out.println(TupperCalc.CalcTupper(i[0], i[1], i[2]) + " Kalorien hinzufügen? [y/n]");
                    choice = scanner.next();
                    if(choice.equals("y"))
                    {
                        if (!Select.SelectFromCaloriesOnDay(connect.connect()).containsKey(localDate.toString())) {
                            Insert.insertCaloriesOnDay(connect.connect(), localDate.toString(), TupperCalc.CalcTupper(i[0], i[1], i[2]));
                        } else {
                            Update.UpdateCaloriesOnDay(connect.connect(), localDate.toString(), TupperCalc.CalcTupper(i[0], i[1], i[2]));
                        }
                    }
                    System.out.println("Tupper löschen? [y/n]");
                    choice = scanner.next();
                    if(choice.equals("y"))
                    {
                        Delete.DeleteFromTupper(connect.connect(),i[3]);
                    }
                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    //shorter doubles
    public static String s(double t)
    {
        String shorter = t + "";
        if(shorter.contains("."))
        {
            shorter = shorter.substring(0,shorter.indexOf(".") +2 );
        }
        if(shorter.contains(","))
        {
            shorter = shorter.substring(0,shorter.indexOf(",") +2 );
        }
        return shorter;
    }
}
