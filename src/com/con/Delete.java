package com.con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    public static void DeleteFromTupper(Connection conn, double ID)
    {
        String sql = "DELETE FROM TUPPER  where ID = ?";


        try (PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setDouble(1,ID);
            preparedStatement.executeUpdate();
        }
        catch(SQLException sq)
        {
            System.out.println(sq.getMessage());
        }
    }
}
