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
}
