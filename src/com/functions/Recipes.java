package com.functions;

import com.SpecialObjects.FullRecipe;
import com.SpecialObjects.Ingredient;
import com.SpecialObjects.Recipe;
import com.con.Connect;
import com.con.Select;

import java.util.LinkedList;
import java.util.Scanner;

public class Recipes
{
    int typeOfUserInterface;
    int localDate;
    Connect connect;
    boolean tupper;
    public Recipes(int typeOfUserInterface, int localDate, Connect connect, boolean tupper)
    {
        this.connect = connect;
        this.localDate = localDate;
        this.typeOfUserInterface = typeOfUserInterface;
        this.tupper = tupper;
    }
    public double UseRecipe() {
        Scanner scanner = new Scanner(System.in);
        int in;
        if (q("Rezept verwenden?")) {
            LinkedList<Recipe> recipes = Select.SelectFromRecipe(connect.connect());
            p("Nummer\t\tDeutscher Name\\Englischer Name");
            for (Recipe recipe : recipes) {
                p(recipe.Number() + "\t\t" + recipe.Name_0() + "\t\t" + recipe.Name_1());
            }
            p("Welches der vielen?");
            in = scanner.nextInt();
            FullRecipe fr = new RecipeFunctions(connect).getFullRecipe(in);
            p(fr.Recipe().Name_0() + " auf Englisch " + fr.Recipe().Name_1());
            p("Deutscher Name Englischer Name Gram");
            for (Ingredient i : fr.Ingredients().keySet()) {
                p(i.Name_0() + " " + i.Name_0() + " " + fr.Ingredients().get(i));
            }
            for (String s : fr.Comments()) {
                p(s);
            }
            p("Gesamtkalorien: " + fr.RecipeCalories());
            if(!tupper) {
                if (q("Auf die Tageskalorien?")) {
                    Repetitions.CheckCalories(connect, localDate, fr.RecipeCalories());
                    return 0.0;
                }
                else
                {
                    return 0.0;
                }
            }
            else
            {
                return fr.RecipeCalories();
            }

        } else {
            return 0;
        }
    }
        public static void p(String s)
        {
            System.out.println(s);
        }
        public static boolean q(String s)
        {
            System.out.println(s);
            return Repetitions.choice();
        }
}
