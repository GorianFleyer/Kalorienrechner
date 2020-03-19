package com.con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update
{
    public static void UpdateCaloriesOnDay(Connection conn, String date, double calories)
    {
        String sql = "UPDATE CaloriesOnDay SET calorie = calorie + ? where date = ?";


        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setDouble(1,calories);
            preparedStatement.setString(2, date);


            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
}
