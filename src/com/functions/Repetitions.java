package com.functions;

import com.SpecialObjects.Ingredient;
import com.connection.Connect;
import com.connection.Insert;
import com.connection.Select;
import com.connection.Update;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Scanner;

public class Repetitions
{
    Connection connect;
    public Repetitions() {
         connect = new Connect().connect();
    }


    public double getCaloriesByName(String name)
    {
        double calories = 0.0;
      // LinkedHashMap<String, Double>[] ingridient =  Select.SelectFromIngridient(connect);
        LinkedList<Ingredient> ingredients =  Select.SelectFromIngridient(connect);

        try
        {
            for(Ingredient i : ingredients)
            {
                if(i.Name_0().equals(name))
                {
                    calories = i.Calories();
                }
                if(i.Name_1().equals(name))
                {
                    calories = i.Calories();
                }
            }

        }
        catch (Exception e)
        {
            p(e.getMessage());
        }

        return calories;
    }
    public boolean getIngredientsContainsName(String name)
    {
        boolean isthere = false;
        // LinkedHashMap<String, Double>[] ingridient =  Select.SelectFromIngridient(connect);
        LinkedList<Ingredient> ingredients =  Select.SelectFromIngridient(connect);

        try
        {
            for(Ingredient i : ingredients)
            {
                if(i.Name_0().equals(name))
                {
                   isthere = true;
                }
                if(i.Name_1().equals(name))
                {
                    isthere = true;
                }
            }

        }
        catch (Exception e)
        {
            p(e.getMessage());
        }

        return isthere;
    }
    public static void p(String s)
    {
        System.out.println(s);
    }
    public static boolean choice()
    {
        String choice = "";
        Scanner scanner = new Scanner(System.in);
        choice = scanner.next();
        if(choice.equals("y"))
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public static void CheckCalories(Connect connect, int localDate, double calories)
    {
        if (!Select.SelectFromCaloriesOnDay(connect.connect()).containsKey(localDate)) {
            Insert.insertCaloriesOnDay(connect.connect(), localDate, calories);
        } else {
            Update.UpdateCaloriesOnDay(connect.connect(), localDate, calories);
        }
    }
    public static void CheckAdditionalCalories(Connect connect, int localDate, double calories)
    {
        if (!Select.SelectFromAdditionalCalories(connect.connect()).containsKey(localDate)) {
            Insert.insertIntoAdditionalCalories(connect.connect(), localDate, calories);
        } else {
            Update.UpdateAdditionalCalories(connect.connect(), localDate, calories);
        }
    }
    public static void ListIngridients(Connect connect)
    {
        LinkedList<Ingredient> ingredients = Select.SelectFromIngridient(connect.connect());
        p("Nummer\t\tName_0\t\tName_1\t\tCalories");
        for(Ingredient i : ingredients)
        {
            p(i.Number() +"\t\t\t" + i.Name_0() + "\t\t" + i.Name_1() + "\t\t" + i.Calories());
        }

    }
}
