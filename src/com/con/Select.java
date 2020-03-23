package com.con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Select {
    public static void SelectFromIngridients(Connection conn) {
        String sql = "SELECT name_0, name_1, calories "
                + "FROM ingridient";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                System.out.println(rs.getString("name_0") + "\t"
                        + rs.getString("name_1") + "\t" + rs.getDouble("calories"));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }


    }
    public static HashMap SelectFromDayWeight(Connection conn) {
        String sql = "SELECT date, weight "
                + "FROM DayWeight";

        HashMap<String, Double> hashMap = new HashMap();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
               hashMap.put(rs.getString("date"),rs.getDouble("weight"));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return hashMap;

    }
    public static HashMap SelectFromCaloriesOnDay(Connection conn) {
        String sql = "SELECT date, calorie "
                + "FROM CaloriesOnDay order by ID";

        HashMap<String, Double> hashMap = new HashMap();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                hashMap.put(rs.getString("date"),rs.getDouble("calorie"));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return hashMap;

    }
}
