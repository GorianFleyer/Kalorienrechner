package com.functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class ChangeToInt
{
    public static void UpdateDayWeightToInt(Connection conn)
    {
        String sql = "SELECT date, id "
                + "FROM DayWeight";

   //     LinkedHashMap<String, Integer> hashMap = new LinkedHashMap();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
  //              hashMap.put(rs.getString(1),rs.getInt(2));

                String sqlUpdate = "UPDATE DayWeight SET DateInt = ? WHERE id = ?";

                try (PreparedStatement preparedStatementUpdate = conn.prepareStatement(sqlUpdate))
                {
                    preparedStatementUpdate.setInt(1,DateCalc.StringtoInt(rs.getString(1)));
                    preparedStatementUpdate.setInt(2, rs.getInt(2));


                    preparedStatementUpdate.executeUpdate();
                }

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }

    }
    public static void UpdateDayCaloriesToInt(Connection conn)
    {
        String sql = "SELECT date, id "
                + "FROM CaloriesOnDay";

        //     LinkedHashMap<String, Integer> hashMap = new LinkedHashMap();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //              hashMap.put(rs.getString(1),rs.getInt(2));

                String sqlUpdate = "UPDATE CaloriesOnDay SET DateInt = ? WHERE id = ?";

                try (PreparedStatement preparedStatementUpdate = conn.prepareStatement(sqlUpdate))
                {
                    preparedStatementUpdate.setInt(1,DateCalc.StringtoInt(rs.getString(1)));
                    preparedStatementUpdate.setInt(2, rs.getInt(2));


                    preparedStatementUpdate.executeUpdate();
                }

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }

    }
    public static void UpdateTupperToInt(Connection conn) {
        String sql = "SELECT date, id "
                + "FROM tupper";

        //     LinkedHashMap<String, Integer> hashMap = new LinkedHashMap();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //              hashMap.put(rs.getString(1),rs.getInt(2));

                String sqlUpdate = "UPDATE tupper SET DateInt = ? WHERE id = ?";

                try (PreparedStatement preparedStatementUpdate = conn.prepareStatement(sqlUpdate)) {
                    preparedStatementUpdate.setInt(1, DateCalc.StringtoInt(rs.getString(1)));
                    preparedStatementUpdate.setInt(2, rs.getInt(2));


                    preparedStatementUpdate.executeUpdate();
                }

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
    }
}
