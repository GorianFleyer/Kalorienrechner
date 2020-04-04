package com.SpecialObjects;

public class BMI
{

    String name_0;
    String name_1;
    double BMI;
    public BMI( String name_0, String name_1, double BMI)
    {

        this.name_0 = name_0;
        this.name_1 = name_1;
        this.BMI = BMI;
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
    public double BMI()
    {
        return BMI;
    }
    public void BMI(double BMI)
    {
        this.BMI = BMI;
    }
}
