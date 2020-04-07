package com.con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update
{
    public static void UpdateCaloriesOnDay(Connection conn, int date, double calories)
    {
        String sql = "UPDATE CaloriesOnDay SET calorie = calorie + ? where dateInt = ?";


        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setDouble(1,calories);
            preparedStatement.setInt(2, date);


            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
    public static void UpdateDayWeight(Connection conn, int date, double weight)
    {
        String sql = "UPDATE DayWeight SET weight = ? WHERE dateInt = ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setDouble(1,weight);
            preparedStatement.setInt(2, date);


            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
    public static void UpdateIngridients(Connection conn, String name_0, String name_1, double calories, boolean lang)
    {
        // lang true = Deutsch
        //
        if(lang) {
            String sql = "UPDATE ingridient Set calories = ? where name_0 = ?";

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setDouble(1, calories);
                preparedStatement.setString(2, name_0);


                preparedStatement.executeUpdate();
                return;
            } catch (SQLException sq) {
                System.out.println(sq.getMessage());
            }
        }
        else
        {
            String sql = "UPDATE ingridient Set calories = ? where name_1 = ?";

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setDouble(1, calories);
                preparedStatement.setString(2, name_1);


                preparedStatement.executeUpdate();
            } catch (SQLException sq) {
                System.out.println(sq.getMessage());
            }
        }
    }
    public static void UpdateAdditionalCalories(Connection conn, int date, double calories)
    {
        String sql = "UPDATE AdditionalCaloriesBurned SET calories = calories + ? WHERE date = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setDouble(1,calories);
            preparedStatement.setInt(2, date);


            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
    public static void UpdateRecipe(Connection conn, String name_0, String name_1, int ID)
    {
        String sql = "UPDATE Recipe SET name_0 =  ?, name_1 = ? WHERE ID = ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setString(1,name_0);
            preparedStatement.setString(2,name_1);
            preparedStatement.setInt(3, ID);


            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
    public static void UpdaterefIngredientRecipe(Connection conn, int fk_recipe, int fk_ingredient, double amount, int ID)
    {
        String sql = "UPDATE refIngredientRecipe SET fk_recipe =  ?, fk_ingredient = ?, amount = ? WHERE ID = ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setInt(1, fk_recipe);
            preparedStatement.setInt(2,fk_ingredient);
            preparedStatement.setDouble(3,amount);
            preparedStatement.setInt(4, ID);


            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }

    }
    public static void UpdateRefRecipeComment(Connection conn, int fk_recipe, String comment, int ID)
    {
        String sql = "UPDATE refRecipeComment SET fk_recipe =  ?, comment = ? WHERE ID = ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setInt(1, fk_recipe);
            preparedStatement.setString(2,comment);
            preparedStatement.setInt(3, ID);


            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
}
