package com.con;

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
    public static void insertDayWeight(Connection conn, String date, double weight)
    {
        String sql = "INSERT INTO DayWeight(date,weight) Values(?,?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setString(1, date);

            preparedStatement.setDouble(2,weight);
            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
    public static void insertCaloriesOnDay(Connection conn, String date, double calories)
    {
        String sql = "INSERT INTO CaloriesOnDay(date,calorie) Values(?,?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setString(1, date);

            preparedStatement.setDouble(2,calories);
            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
}
