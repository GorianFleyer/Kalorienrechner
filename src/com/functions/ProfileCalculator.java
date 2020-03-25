package com.functions;

import com.con.Connect;
import com.con.Insert;
import com.con.Select;
import com.fg.Calculator;

import java.sql.Connection;
import java.time.LocalDate;

public class ProfileCalculator {

    public static void Calc(Connect connect, LocalDate localDate, double weight, double size,int sex, int age, double pal)
    {
        double caloriesOnday= 0.0;
        double difference= 0.0;
        caloriesOnday = Calculator.CalorieRequired(weight,size,sex,age,pal);
        if (!Select.SelectFromCaloriesOnDay(connect.connect()).containsKey(localDate.toString())) {

            Insert.insertCaloriesOnDay(connect.connect(), localDate.toString(), 0.0);
        }

        difference = caloriesOnday - (double) Select.SelectFromCaloriesOnDay(connect.connect()).get(localDate.toString());
        System.out.println("Der Kalorienverbrauch pro Tag liegt bei " + caloriesOnday + " Kalorien am Tag");

        System.out.println("Sie können noch " + difference + " Kalorien zu sich nehmen.");
    }
}
