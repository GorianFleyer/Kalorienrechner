package com.SpecialObjects;

import com.fg.Calculator;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class FullRecipe {
    Recipe recipe;
   LinkedHashMap<Ingredient,Double> ingredients;
    LinkedList<String> comments;

    public Recipe Recipe() {
        return recipe;
    }

    public FullRecipe(Recipe recipe, LinkedHashMap<Ingredient, Double> ingredients, LinkedList<String> comments) {
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.comments = comments;

    }

    public Recipe Number() {
        return recipe;
    }

    public void Recipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public LinkedHashMap<Ingredient, Double> Ingredients() {
        return ingredients;
    }

    public void Ingredients(LinkedHashMap<Ingredient,Double> ingredients) {
        this.ingredients = ingredients;
    }

    public LinkedList<String> Comments() {
        return comments;
    }

    public void Comments(LinkedList<String> comments) {
        this.comments = comments;
    }
    public double RecipeCalories()
    {
        double calories=0.0;
        for(Ingredient i : ingredients.keySet())
        {
            calories += Calculator.Sum(ingredients.get(i),i.Calories());
        }

        return calories;
    }
}
