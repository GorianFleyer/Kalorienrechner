package com.con;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect
{
    public static Connection connect()
    {
    Connection conn = null;
        try
        {

            String url = "jdbc:sqlite:database/database";
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException  e)
        {
            System.out.println(e.getMessage());
        }

        return conn;
    }

}
