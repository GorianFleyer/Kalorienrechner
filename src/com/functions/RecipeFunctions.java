package com.functions;

import com.SpecialObjects.FullRecipe;
import com.SpecialObjects.Ingredient;
import com.SpecialObjects.Recipe;
import com.connection.Connect;
import com.connection.Insert;
import com.connection.Select;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class RecipeFunctions
{
    Connect connect;
    public RecipeFunctions(Connect connect)
    {
        this.connect = connect;
    }
    public void CreateRecipe(int localdate)
    {
        Scanner scanner = new Scanner(System.in);
        String name0 = "";
        String name1 = "";
        String comment = "";
        int gotId = 0;
        double calories= 0.0;
        LinkedHashMap<Ingredient, Double> IngredientList = new LinkedHashMap<>();
        try {
            p("Neues Rezept anlegen?");
            if (Repetitions.choice()) {
                p("Deutscher Name?");
                name0 = scanner.nextLine();
                p("Englischer Name");
                name1 = scanner.nextLine();
                for( Recipe i : Select.SelectFromRecipe(connect.connect()))
                {
                    if(i.Name_0().equals(name0))
                    {
                        p("Der deutsche Name ist schon vergeben. Neuer Name?");
                        {
                            if(Repetitions.choice())
                            {
                                p("Neuer Name?");
                                name0 = scanner.nextLine();
                            }
                            else
                            {
                                p("Überschreiben?");
                                if(Repetitions.choice())
                                {
                                    gotId = i.Number();
                                }
                            }
                        }
                    }
                    if(i.Name_1().equals(name1))
                    {
                        p("Der englische Name ist schon vergeben. Neuer Name?");
                        {
                            if(Repetitions.choice())
                            {
                                p("Neuer Name?");
                                name1 = scanner.nextLine();
                            }
                            else
                            {
                                p("Überschreiben?");
                                if(Repetitions.choice())
                                {
                                    gotId = i.Number();
                                }
                            }
                        }
                    }
                }
                if(gotId == 0)
                {
                    Insert.insertIntoRecipes(connect.connect(),name0,name1);
                    LinkedList<Recipe> recipes = Select.SelectFromRecipe(connect.connect());
                    for(Recipe r : recipes)
                    {
                        if(r.Name_0().equals(name0))
                        {
                            gotId = r.Number();
                            AddIngredient(connect,gotId);

                        }
                    }
                    if(q("Komentar hinzufügen?"))
                    {
                        comment = scanner.nextLine();
                        if(q("Einfügen?")) {
                            Insert.insertIntoRefRecipeComment(connect.connect(), gotId, comment);
                        }
                    }
                    if(q("Gleich zu den Tageskalorien?"))
                    {
                        Repetitions.CheckAdditionalCalories(connect, localdate,getFullRecipe(gotId).RecipeCalories());
                    }
                }
            }
        }
        catch (Exception e)
        {
            p(e.getMessage());
        }
    }
    public void AddIngredient(Connect connect, int id)
    {
        Scanner scanner = new Scanner(System.in);
        int ingredient = 0;
        double amount = 0.0;
        if(q("Neue Zutat hinzufügen?"))
        {

            p("Zutaten auflisten?");

            if(Repetitions.choice())
            {
                Repetitions.ListIngridients(connect);
            }
        p("Welche Zutat?");
            ingredient = scanner.nextInt();
            p("Wieviel davon?");
            amount = scanner.nextDouble();
            if(q("Hinzufügen?"))
            {
                Insert.insertIntoRefIngredientRecipe(connect.connect(),id,ingredient,amount);
            }
            AddIngredient(connect, id);

        }
        else
        {
            if(q("Zutat entfernen?")) {
                //kb
                AddIngredient(connect, id);
            }

        }

    }
    public FullRecipe getFullRecipe(int id)
    {
        // FullRecipe(Recipe recipe, LinkedHashMap<Ingredient, Double> ingredients, LinkedList<String> comments)
        Recipe recipe = Select.SelectSingleRecipe(connect.connect(),id);
        LinkedHashMap<Ingredient, Double> ingredients = Select.SelectIngredientRecipe(connect.connect(),id);
        LinkedList<String> comments = Select.SelectRefRecipeComment(connect.connect(),id);
        FullRecipe temp = new FullRecipe(recipe,ingredients,comments);
        return temp;
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
