package com.con;

import com.SpecialObjects.BMI;
import com.SpecialObjects.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
        String sql = "SELECT dateInt, weight "
                + "FROM DayWeight";

        LinkedHashMap<Integer, Double> hashMap = new LinkedHashMap();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
               hashMap.put(rs.getInt("dateInt"),rs.getDouble("weight"));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return hashMap;

    }
    public static LinkedHashMap SelectFromCaloriesOnDay(Connection conn) {
        String sql = "SELECT dateInt, calorie "
                + "FROM CaloriesOnDay order by ID";

        LinkedHashMap<Integer, Double> hashMap = new LinkedHashMap();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                hashMap.put(rs.getInt("dateInt"),rs.getDouble("calorie"));

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
    public static LinkedHashMap<Integer, Double>  SelectFromAdditionalCalories(Connection conn) {
        String sql = "SELECT date, calories  "
                + "FROM AdditionalCaloriesBurned order by ID";


        LinkedHashMap<Integer, Double> additionalCalories = new LinkedHashMap<Integer,Double>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                additionalCalories.put(rs.getInt(1),rs.getDouble(2));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return additionalCalories;

    }

    public static LinkedList<Ingredient>  SelectFromIngridient(Connection conn) {
        String sql = "SELECT name_0, name_1, calories  "
                + "FROM ingridient order by ID";


        LinkedList<Ingredient> ingridients = new LinkedList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();

            int count = 0;
            while (rs.next()) {


                ingridients.add(new Ingredient(count,rs.getString(1),rs.getString(2),rs.getDouble(3)));
                count++;

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return ingridients;

    }
    public static LinkedList<BMI> SelectBMIAdult(Connection conn) {
        String sql = "SELECT name_0, name_1, BMI "
                + "FROM BMIAdult order by ID";

        LinkedList<BMI> BMIs = new LinkedList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                BMIs.add(new BMI(rs.getString(1),rs.getString(2),rs.getDouble(3)));

            }

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }
        return BMIs;

    }
}
