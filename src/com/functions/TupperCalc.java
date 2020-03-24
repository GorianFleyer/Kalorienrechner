package com.functions;

public class TupperCalc {
    public static double CalcTupper(double calories, double fullweight, double tupperweight)
    {
        double calc=0.0;
        calc = calories / fullweight;
        calc = calc * tupperweight;

        return calc;
    }
}
