package com.functions;

import java.time.LocalDate;

public class DateCalc
{
    public static int toInt(LocalDate localDate)
    {
        return Integer.parseInt(localDate.toString().replace("-", ""));
    }
    public static int StringtoInt(String localDate)
    {
        return Integer.parseInt(localDate.replace("-", ""));
    }
    public static String cToString(int date)
    {
        String temp = Integer.toString(date);
        temp = temp.substring(0,4) + "-" + temp.substring(4,6) + "-" + temp.substring(6);

        return temp;

    }
    public static String GermanDate(int date)
    {
        String temp = Integer.toString(date);
        temp = temp.substring(6) + "." + temp.substring(4,6) + "." + temp.substring(0,4)  ;

        return temp;

    }
    public static int RevertGermanDate(String germanDate)
    {
        int temp = 0;
        germanDate = germanDate.substring(6)+ germanDate.substring(3,5) + germanDate.substring(0,2);
        try
        {
            temp = Integer.parseInt(germanDate);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return temp;

    }
}
