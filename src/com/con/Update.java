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
        String sql = "UPDATE AdditionalCaloriesBurned SET calories = calories + ? WHERE date = ?)";

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
}
