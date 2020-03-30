package com.con;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

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
    public static LinkedHashMap SelectFromDayWeight(Connection conn) {
        String sql = "SELECT date, weight "
                + "FROM DayWeight";

        LinkedHashMap<String, Double> hashMap = new LinkedHashMap();
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
    public static LinkedHashMap SelectFromCaloriesOnDay(Connection conn) {
        String sql = "SELECT date, calorie "
                + "FROM CaloriesOnDay order by ID";

        LinkedHashMap<String, Double> hashMap = new LinkedHashMap();
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
    public static List<double[]> SelectFromTupper(Connection conn) {
        String sql = "SELECT ID,calories, fullweight, tupperweight "
                + "FROM tupper order by ID";

        List<double[]> tupperList = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();

            int listCount = 0;
            while (rs.next()) {

                double[] temp = {rs.getDouble("calories"), rs.getDouble("fullweight"), rs.getDouble("tupperweight"), rs.getDouble("ID")};
                tupperList.add(temp);



            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return tupperList;

    }
    public static LinkedHashMap<String, double[]>  SelectFromprofile(Connection conn) {
        String sql = "SELECT login, size, pal,age,sex  "
                + "FROM profile order by ID";


        LinkedHashMap<String, double[]> profile = new LinkedHashMap<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {

                double[] temp = {rs.getDouble("size"), rs.getDouble("pal"), rs.getDouble("age"),rs.getDouble("sex")};

                profile.put(rs.getString("login"), temp);


            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return profile;

    }
}
