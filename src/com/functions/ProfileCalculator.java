package com.functions;

import com.connection.Connect;
import com.connection.Insert;
import com.connection.Select;
import com.fg.Calculator;

public class ProfileCalculator {

    public static void Calc(Connect connect, int localDate, double weight, double size,int sex, int age, double pal)
    {
        double caloriesOnday= 0.0;
        double difference= 0.0;
        caloriesOnday = Calculator.CalorieRequired(weight,size,sex,age,pal);
        if (!Select.SelectFromCaloriesOnDay(connect.connect()).containsKey(localDate)) {

            Insert.insertCaloriesOnDay(connect.connect(), localDate, 0.0);
        }

        difference = caloriesOnday - (double) Select.SelectFromCaloriesOnDay(connect.connect()).get(localDate);
        System.out.println("Der Kalorienverbrauch pro Tag liegt bei " + caloriesOnday + " Kalorien am Tag");

        System.out.println("Sie können noch " + difference + " Kalorien zu sich nehmen.");
    }
}
