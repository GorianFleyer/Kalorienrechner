package com.con;

import com.SpecialObjects.BMI;
import com.SpecialObjects.Ingredient;
import com.SpecialObjects.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Select {

    public static LinkedHashMap SelectFromDayWeight(Connection conn) {
        String sql = "SELECT dateInt, weight "
                + "FROM DayWeight";

        LinkedHashMap<Integer, Double> hashMap = new LinkedHashMap();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
               hashMap.put(rs.getInt("dateInt"),rs.getDouble("weight"));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return hashMap;

    }
    public static LinkedHashMap SelectFromCaloriesOnDay(Connection conn) {
        String sql = "SELECT dateInt, calorie "
                + "FROM CaloriesOnDay order by ID";

        LinkedHashMap<Integer, Double> hashMap = new LinkedHashMap();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                hashMap.put(rs.getInt("dateInt"),rs.getDouble("calorie"));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return hashMap;

    }
    public static List<double[]> SelectFromTupper(Connection conn) {
        String sql = "SELECT ID,calories, fullweight, tupperweight "
                + "FROM tupper order by ID";

        List<double[]> tupperList = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {

                double[] temp = {rs.getDouble("calories"), rs.getDouble("fullweight"), rs.getDouble("tupperweight"), rs.getDouble("ID")};
                tupperList.add(temp);



            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return tupperList;

    }
    public static LinkedHashMap<String, double[]>  SelectFromprofile(Connection conn) {
        String sql = "SELECT login, size, pal,age,sex  "
                + "FROM profile order by ID";


        LinkedHashMap<String, double[]> profile = new LinkedHashMap<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {

                double[] temp = {rs.getDouble("size"), rs.getDouble("pal"), rs.getDouble("age"),rs.getDouble("sex")};

                profile.put(rs.getString("login"), temp);


            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return profile;

    }
    public static LinkedHashMap<Integer, Double>  SelectFromAdditionalCalories(Connection conn) {
        String sql = "SELECT date, calories  "
                + "FROM AdditionalCaloriesBurned order by ID";


        LinkedHashMap<Integer, Double> additionalCalories = new LinkedHashMap<Integer,Double>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                additionalCalories.put(rs.getInt(1),rs.getDouble(2));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return additionalCalories;

    }

    public static LinkedList<Ingredient>  SelectFromIngridient(Connection conn) {
        String sql = "SELECT name_0, name_1, calories, ID  "
                + "FROM ingridient order by ID";


        LinkedList<Ingredient> ingridients = new LinkedList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {


                ingridients.add(new Ingredient(rs.getInt(4),rs.getString(1),rs.getString(2),rs.getDouble(3)));


            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return ingridients;

    }
    public static LinkedList<BMI> SelectBMIAdult(Connection conn) {
        String sql = "SELECT name_0, name_1, BMI "
                + "FROM BMIAdult order by ID";

        LinkedList<BMI> BMIs = new LinkedList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                BMIs.add(new BMI(rs.getString(1),rs.getString(2),rs.getDouble(3)));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return BMIs;

    }
    public static LinkedList<Recipe> SelectFromRecipe(Connection conn)
    {
        String sql = "SELECT name_0, name_1, ID "
                + "FROM recipe order by ID";

        LinkedList<Recipe> recipes = new LinkedList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                recipes.add(new Recipe(rs.getInt(3),rs.getString(1),rs.getString(2)));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return recipes;
    }
    public static Recipe SelectSingleRecipe(Connection conn, int ID)
    {
        String sql = "SELECT name_0, name_1, ID "
                + "FROM recipe where ID =" + ID;

        Recipe recipe = new Recipe(0,"","");
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                recipe = new Recipe(rs.getInt(3),rs.getString(1),rs.getString(2));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return recipe;
    }
    public static LinkedHashMap<Ingredient, Double> SelectIngredientRecipe(Connection conn, int ID)
    {
        String sql = "SELECT ingridient.id, ingridient.name_0, ingridient.name_1, ingridient.calories, refIngredientRecipe.amount "
                + "FROM refIngredientRecipe  "
                + "INNER JOIN ingridient ON ingridient.ID = refIngredientRecipe.fk_ingredient "
                + "where refIngredientRecipe.fk_recipe = " + ID;


        LinkedHashMap<Ingredient,Double> temp = new LinkedHashMap<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {

                temp.put(new Ingredient(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4)),rs.getDouble(5));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return temp;
    }
    public static LinkedList<String> SelectRefRecipeComment(Connection conn, int ID)
    {
        String sql = "SELECT rC.comment "
                + "FROM recipe r "
                + "INNER JOIN refRecipeComment rC ON r.ID = rC.fk_recipe "
                + "where rC.fk_recipe = " + ID;


        LinkedList<String> temp = new LinkedList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {

                temp.add(rs.getString(1));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return temp;
    }
}
