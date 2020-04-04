package com.fg;

public class Calculator
{


    // Relation: x Gram * y Calories (per 100g) = xy Gram Calories / 100
    // Knowledge of grams and calorie per 100g (Ratio)= sum

    public static double Sum( double gram, double ratio) throws ArithmeticException
    {
        return gram * ratio / 100;
    }

    // Reach the ratio of Calories per 100g
    // Knowledge of calorie intake and gram intake

    public static double Ratio(double gram, double sum) throws ArithmeticException
    {
        return sum * 100 / gram;
    }

    // Knowledge of Ratio and Calorie intake = gram

    public static double Gram(double sum, double ratio) throws ArithmeticException
    {

            return sum * 100 / ratio;


    }

    public static double AddToDailyCal(double dailycal, double addition)
    {
        return dailycal + addition;
    }

    public static double CalorieRequired(double weight, double size, int sex, int age, double pal)
    {
        // Sex 1 = Male Sex 2 = Female
        /*
        * Körperliche Belastung 	PALWert
Schlafen 	0,95
Nur Sitzen oder Liegen 	1,2
Ausschließlich sitzende Tätigkeit mit wenig oder keiner körperlichen Aktivität in der Freizeit, z.B. Büroarbeit 	1,4 – 1,5
Sitzende Tätigkeit mit zeitweilig gehender oder stehender Tätigkeit, z.B. Studierende, Fließbandarbeiter, Laboranten, Kraftfahrer 	1,6 – 1,7
Überwiegend gehende oder stehende Tätigkeit, z.B. Verkäufer, Kellner, Handwerker, Mechaniker, Hausfrauen 	1,8 – 1,9
Körperlich anstrengende berufliche Arbeit 	2,0 – 2,4
        *
        *
        * */


        if(sex==1)
        {
            return (66.47 + (13.7 * weight) + (5 * size) - (6.8 * age)) * pal;
        }
        else
        {
            return (655.1 + (9.6 * weight) + (1.8 * size) - (4.7 * age)) * pal;
        }
    }
//https://de.wikipedia.org/wiki/Body-Mass-Index

    // BMI = weight / size²
    // weight = BMI * size²

    public static double BMI(double size, double weight) throws ArithmeticException
    {
        return weight / (size * size);
    }
    public static double AimedWeight(double size, double BMI) throws ArithmeticException
    {
        return BMI * (size * size);
    }

    public static double calorietoFat(double calorie)
    {
        return calorie / 7000;
    }

    public static double FatToCalories(double fat)
    {
        return fat * 7000;
    }
    // Estimates a new PAL
    public static double PAL(double estimatedCalories,double calculatedCalories, double oldPal)
    {
        return estimatedCalories * oldPal / calculatedCalories;
    }
}
