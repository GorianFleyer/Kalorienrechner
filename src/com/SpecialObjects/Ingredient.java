package com.SpecialObjects;

public class Ingredient
{
    int number;
    String name_0;
    String name_1;
    double calories;
    public Ingredient(int number, String name_0, String name_1, double calories)
    {
        this.number = number;
        this.name_0 = name_0;
        this.name_1 = name_1;
        this.calories = calories;
    }
    public int Number()
    {
        return number;
    }
    public void Number(int number)
    {
        this.number = number;
    }
    public String Name_0()
    {
        return name_0;
    }
    public void Name_0(String name_0)
    {
        this.name_0 = name_0;
    }
    public String Name_1()
    {
        return name_1;
    }
    public void Name_1(String name_1)
    {
        this.name_1 = name_1;
    }
    public double Calories()
    {
        return calories;
    }
    public void Calories(double calories)
    {
        this.calories = calories;
    }
}
