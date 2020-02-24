package com.fg;

public class Calculator
{


    // Relation: x Gram * y Calories (per 100g) = xy Gram Calories / 100
    // Knowledge of grams and calorie per 100g (Ratio)= sum

    public static double Sum( double gram, double ratio)
    {
        return gram * ratio / 100;
    }

    // Reach the ratio of Calories per 100g
    // Knowledge of calorie intake and gram intake

    public static double Ratio(double gram, double sum)
    {
        return sum * 100 / gram;
    }

    // Knowledge of Ratio and Calorie intake = gram

    public static double Gram(double sum, double ratio)
    {
        return sum * 100 / ratio;
    }

    public static double AddToDailyCal(double dailycal, double addition)
    {
        return dailycal + addition;
    }

}
