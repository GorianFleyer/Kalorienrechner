package com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert
{
    public static void insertIntoIngredient(Connection conn, String name_0, String name_1, double calories)
    {
    String sql = "INSERT INTO ingridient(name_0,name_1, calories) Values(?,?,?)";

    try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
    {
        preparedStatement.setString(1, name_0);
        preparedStatement.setString(2,name_1);
        preparedStatement.setDouble(3,calories);
        preparedStatement.executeUpdate();
    }
    catch(SQLException sq)
    {
        System.out.println(sq.getMessage());
    }
}
    public static void insertDayWeight(Connection conn, int date, double weight)
    {
        String sql = "INSERT INTO DayWeight(dateInt,weight) Values(?,?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setInt(1, date);

            preparedStatement.setDouble(2,weight);
            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
    public static void insertCaloriesOnDay(Connection conn, int date, double calories)
    {
        String sql = "INSERT INTO CaloriesOnDay(dateInt,calorie) Values(?,?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setInt(1, date);

            preparedStatement.setDouble(2,calories);
            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
    public static void insertTupper(Connection conn, int date, double calories, double fullweight, double tupperweight)
    {
        String sql = "INSERT INTO tupper(dateInt,calories,fullweight,tupperweight) Values(?,?,?,?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setInt(1, date);
            preparedStatement.setDouble(2,calories);
            preparedStatement.setDouble(3,fullweight);
            preparedStatement.setDouble(4,tupperweight);
            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
    public static void insertProfile(Connection conn, String login, double size, double pal, double age,int sex)
    {
        String sql = "INSERT INTO profile (login,size,pal,age,sex) Values (?,?,?,?,?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setString(1, login);
            preparedStatement.setDouble(2,size);
            preparedStatement.setDouble(3,pal);
            preparedStatement.setDouble(4,age);
            preparedStatement.setDouble(5,sex);
            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
    public static void insertIntoAdditionalCalories(Connection conn, int date, double calories)
    {
        String sql = "INSERT INTO AdditionalCaloriesBurned (date, calories) Values (?,?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setInt(1, date);
            preparedStatement.setDouble(2,calories);
            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
    public static void insertIntoRecipes(Connection conn, String name_0, String name_1)
    {
        String sql = "INSERT INTO recipe (name_0, name_1) Values (?,?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setString(1, name_0);
            preparedStatement.setString(2,name_1);
            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
    public static void insertIntoRefIngredientRecipe(Connection conn, int fk_recipe, int fk_ingredient, double amount)
    {
        String sql = "INSERT INTO refIngredientRecipe (fk_recipe, fk_ingredient, amount) Values (?,?,?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setInt(1, fk_recipe);
            preparedStatement.setInt(2,fk_ingredient);
            preparedStatement.setDouble(3,amount);
            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
    public static void insertIntoRefRecipeComment(Connection conn, int fk_recipe, String comment)
    {
        String sql = "INSERT INTO refRecipeComment (fk_recipe, comment) Values (?,?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setInt(1, fk_recipe);
            preparedStatement.setString(2,comment);
            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
}
