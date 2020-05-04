package com.Panel;

import com.SpecialObjects.Ingredient;
import com.con.Connect;
import com.con.Delete;
import com.con.Insert;
import com.con.Select;
import com.functions.DateCalc;
import com.functions.Repetitions;
import com.functions.TupperCalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.LinkedList;

public class RecipeBar extends JPanel {
    int localdate;
    Connect connect;
    LinkedList<Ingredient> ingredients;
    List<double[]> tupperList;
    JTable tableIngredients;
    JTable tableTupper;
    String[] columnNamesIngredients;
    String[] columnNamesTupper;
    String[][] tableDataIngredients;
    Double[][] tableDataTupper;
    JButton addToCaloriesButton;
    JButton tupperButton;
    JButton eatTupperButton;
    JTextField tfFullCalories;
    JTextField tfEaten;
    JTextField tfTuppered;
    JLabel tupperLabelTuppered;
    JLabel tupperLabelEaten;
    JLabel caloriesLabel;
    double fullCalories;
    public RecipeBar(int localdate, Connect connect)
    {
        caloriesLabel = new JLabel("gegessene Kalorien");
        tupperLabelEaten = new JLabel("Gramm gegessen");
        tupperLabelTuppered = new JLabel("Gram getuppert");

        fullCalories = 0.0;
        setLayout(new FlowLayout());
        this.connect = connect;
        this.localdate = localdate;
        ingredients = Select.SelectFromIngridient(connect.connect());
        tupperList =  Select.SelectFromTupper(connect.connect());
        JButton testbutton = new JButton("test");
        testbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                testbutton();
            }
        });
        addToCaloriesButton = new JButton("Zu den Tageskalorien");
        addToCaloriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addCalorieAction();
            }
        });
        tupperButton = new JButton("Eintuppern");
        tupperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tupperAction();
            }
        });
        eatTupperButton = new JButton("Tupper essen");
        eatTupperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                eatTupperAction();
            }
        });
        columnNamesIngredients = new String[]{"Deutscher Name", "Englischer Name", "Kalorien", "Menge"};
        tableDataIngredients = new String[ingredients.size()+1][4];
        for (Ingredient i: ingredients) {
            tableDataIngredients[i.Number()-1][0] = i.Name_0();
            tableDataIngredients[i.Number()-1][1] = i.Name_1();
            tableDataIngredients[i.Number()-1][2] = i.Calories() + "";
            tableDataIngredients[i.Number()-1][3] = "";

        }
        tableDataIngredients[ingredients.size()][0] = "";
        tableDataIngredients[ingredients.size()][1] = "";
        tableDataIngredients[ingredients.size()][2] = "";
        tableDataIngredients[ingredients.size()][3] = "";
        tfFullCalories = new JTextField(fullCalories+"",8);
        tfEaten = new JTextField("",9);
        tfTuppered = new JTextField("",10);
        tableIngredients = new JTable(tableDataIngredients, columnNamesIngredients);
        tableIngredients.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {

                // TODO Auslagern
                int col = 0;
                int row = 0;
                fullCalories = 0.0;
                row = tableIngredients.getEditingRow();
                col = tableIngredients.getEditingColumn();
                if(col==3) {

                    fullCalories = tableCalcAction();
                    tfFullCalories.setText(s(fullCalories) + "");
                    tfFullCalories.repaint();

                }
                if(row==ingredients.size())
                {
                    addIngriedientAction();
                }

            }
        });
        columnNamesTupper = new String[]{"Id","Kalorien insgesamt", "Vollgewicht", "Tuppergewicht"};
        tableDataTupper = new Double[tupperList.size()][4];
        for(double[] d : tupperList)
        {
            tableDataTupper[tupperList.indexOf(d)][0]= d[3];
            tableDataTupper[tupperList.indexOf(d)][1]= d[0];
            tableDataTupper[tupperList.indexOf(d)][2]= d[1];
            tableDataTupper[tupperList.indexOf(d)][3]= d[2];
            // double[] temp = {rs.getDouble("calories"), rs.getDouble("fullweight"), rs.getDouble("tupperweight"), rs.getDouble("ID")};
            // d0 calories, d1 fullweight, d2 tupperweight, d3 id
        }
        tableTupper = new JTable(tableDataTupper, columnNamesTupper);
        ListSelectionModel cellSelectionModel = tableTupper.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


//         label.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//    textField.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tupperLabelTuppered.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        tfTuppered.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        tupperLabelEaten.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        tfEaten.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        caloriesLabel.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        tfFullCalories.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JPanel calPanel = new JPanel();
        JPanel tupperEatenPanel = new JPanel();
        JPanel tupperedPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel textfields = new JPanel();
        textfields.setLayout(new GridLayout(4,1));
        JScrollPane scrollPaneRecipe = new JScrollPane(tableIngredients);
        JScrollPane scrollPaneTupper = new JScrollPane(tableTupper);
        calPanel.add(caloriesLabel);
        calPanel.add(tfFullCalories);
        tupperEatenPanel.add(tupperLabelEaten);
        tupperEatenPanel.add(tfEaten);
        tupperedPanel.add(tupperLabelTuppered);
        tupperedPanel.add(tfTuppered);
        buttonPanel.add(addToCaloriesButton);
        buttonPanel.add(tupperButton);
        buttonPanel.add(eatTupperButton);
        add(scrollPaneRecipe);
        add(scrollPaneTupper);
        textfields.add(tupperEatenPanel);
        textfields.add(tupperedPanel);
        textfields.add(calPanel);

        add(textfields);
        add(buttonPanel);



      //  add(testbutton);



    }
    //shorter doubles
    public static String s(double t)
    {
        String shorter = t + "";
        if(shorter.contains("."))
        {
            shorter = shorter.substring(0,shorter.indexOf(".") +2 );
        }
        if(shorter.contains(","))
        {
            shorter = shorter.substring(0,shorter.indexOf(",") +2 );
        }
        return shorter;
    }
    public void tupperAction()
    {
        fullCalories = tableCalcAction();
        double tupperweight = 0.0;
        double fullweight = 0.0;
        try
        {
            tupperweight = Double.parseDouble(tfTuppered.getText());
            fullweight = Double.parseDouble(tfEaten.getText()) + tupperweight;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(tableIngredients, e.getMessage());
        }
        double tupperCalories = TupperCalc.CalcTupper(fullCalories, fullweight, tupperweight);
        int dialog = JOptionPane.showConfirmDialog(null,
                "Kalorien insgesamt: " + s(fullCalories)
                + "\nHeute gegessen: " + tfEaten.getText()
                        +"\nDas sind " + tupperCalories + "Kalorien"
                + "\nGetuppert: " + tfTuppered.getText()
                + "\nAuf den Tageswert und dann eintuppern?",
                "Tupper",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if(dialog == 0)
        {
            Repetitions.CheckCalories(connect,localdate,tupperCalories);
            Insert.insertTupper(connect.connect(), localdate, fullCalories, fullweight, tupperweight);
            JOptionPane.showMessageDialog(tableIngredients, "Ok");
        }


    }
    public void addCalorieAction()
    {
        fullCalories = tableCalcAction();
        int dialog = JOptionPane.showConfirmDialog(null,
                "Kalorien insgesamt: " + s(fullCalories)
                + "\nEin tragen für " + DateCalc.GermanDate(localdate) + "?",
                "Eintragen",
                JOptionPane.YES_NO_OPTION);
        if(dialog == 0)
        {
            Repetitions.CheckCalories(connect,localdate,fullCalories);
            JOptionPane.showMessageDialog(tableIngredients, "Ok");
        }
    }
    public double tableCalcAction()
    {
        double temp = 0.0;
        for(int i = 0; i < tableIngredients.getRowCount(); i++) {
            try {
                if(!(tableIngredients.getValueAt(i, 2).toString().isBlank() || tableIngredients.getValueAt(i, 3).toString().isBlank()))
                {
                    temp += Double.parseDouble(tableIngredients.getValueAt(i, 2).toString())
                            * Double.parseDouble(tableIngredients.getValueAt(i, 3).toString()) / 100;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(tableIngredients, e.getMessage());
            }
        }
            return temp;
    }

    public void testbutton()
    {
        JFrame frame = new JFrame();
        String[] options = new String[4];
        options[0] = new String("Tuppern und eintragen");
        options[1] = new String("Eintragen");
        options[2] = new String("Nur tuppern");
        options[3] = new String("Abbrechen");
        int dialog = JOptionPane.showOptionDialog(frame.getContentPane(),"Message!","Title",
                0,JOptionPane.YES_NO_CANCEL_OPTION,null,options,null);
        if(dialog == 0)
        {
            JOptionPane.showMessageDialog(tableIngredients, "Ok");
        }
        if(dialog == 1)
        {
            JOptionPane.showMessageDialog(tableIngredients, "Not ok, Ok");
        }


    }
    public void addIngriedientAction()
    {
       int  col = 0;
       int row = ingredients.size();

        row = tableIngredients.getEditingRow();
        col = tableIngredients.getEditingColumn();

       JOptionPane.showMessageDialog(tableIngredients, tableIngredients.getValueAt(row,col).toString());

    }
    public void eatTupperAction()
    {
        int selectedRow = tableTupper.getSelectedRow();
        try {
            double tupperRestWeight = Double.parseDouble(tableTupper.getValueAt(selectedRow, 3).toString());
            double tupperCal = Double.parseDouble(tableTupper.getValueAt(selectedRow, 1).toString());
            double tupperFullWeight = Double.parseDouble(tableTupper.getValueAt(selectedRow, 2).toString());
            double tupperId = Double.parseDouble(tableTupper.getValueAt(selectedRow, 0).toString());
            double fullCal = TupperCalc.CalcTupper(tupperCal, tupperFullWeight, tupperRestWeight);
            JFrame frame = new JFrame();
            String[] options = new String[4];
            options[0] = new String("Tupper essen und eintragen");
            options[1] = new String("Eintragen");
            options[2] = new String("Nur tupper löschen");
            options[3] = new String("Abbrechen");
            int dialog = JOptionPane.showOptionDialog(frame.getContentPane(),"Tupper Restgewicht: " + s(tupperRestWeight)
                    + "\nTupperkalorien: " + s(tupperCal)
                    + "\nTuppervollgewicht: " +s(tupperFullWeight)
                    + "\nKalorien in der Tupper: " + s(fullCal)
                    + "\nTupper-ID: " +tupperId,
                    "Tupper essen",
                    0,JOptionPane.YES_NO_CANCEL_OPTION,null,options,null);
            if(dialog == 0)
            {
                Repetitions.CheckCalories(connect,localdate,fullCal);
                Delete.DeleteFromTupper(connect.connect(),tupperId);
                JOptionPane.showMessageDialog(tableTupper, "Ok");
                /*                    if(choice.equals("y"))
                    {
                        Repetitions.CheckCalories(connect,localDate,TupperCalc.CalcTupper(i[0],i[1],i[2]));

                    }
                    System.out.println("Tupper löschen? [y/n]");

                    if (Repetitions.choice())
                    {
                        Delete.DeleteFromTupper(connect.connect(),i[3]);
                    }*/

            }
            if(dialog == 1)
            {
                JOptionPane.showMessageDialog(tableTupper, "Not ok, Ok");
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(tableTupper, e.getMessage());
        }

    }
        /*
         *     ListSelectionModel cellSelectionModel = table.getSelectionModel();
    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        String selectedData = null;

        int[] selectedRow = table.getSelectedRows();
        int[] selectedColumns = table.getSelectedColumns();
        List<double[]> tupper = Select.SelectFromTupper(connect.connect());
            for (double[] i : tupper) {
                System.out.println("Tupper mit " + i[0] + "Kalorien auf " + i[1] + "g enthält noch " + i[2] + " Gramm");
                System.out.println("Das sind " + TupperCalc.CalcTupper(i[0], i[1], i[2]) + " Kalorien");
                System.out.println("ID: " + i[3]);

            }
            System.out.println("Welche Tupper möchten Sie essen?");
            input = scanner.nextInt();
            for (double[] i : tupper)
            {
                if(input == i[3])
                {
                    // @Todo Hier eine Auswahl einfügen, ob alles zu essen ist
                    System.out.println(TupperCalc.CalcTupper(i[0], i[1], i[2]) + " Kalorien hinzufügen? [y/n]");
                    choice = scanner.next();
                    if(choice.equals("y"))
                    {
                        Repetitions.CheckCalories(connect,localDate,TupperCalc.CalcTupper(i[0],i[1],i[2]));

                    }
                    System.out.println("Tupper löschen? [y/n]");

                    if (Repetitions.choice())
                    {
                        Delete.DeleteFromTupper(connect.connect(),i[3]);
                    }
                }
        * */
           /*                 System.out.println("Wie viel haben Sie davon gegessen?");
                fullweight = scanner.nextDouble();
                System.out.println("Wie viel haben Sie getuppert?");
                tupperweight = scanner.nextDouble();
                fullweight += tupperweight;
                tupperCalories = TupperCalc.CalcTupper(counter, fullweight, tupperweight);

                System.out.println("Heute haben Sie " + tupperCalories + " Kalorien gegessen. Auf den Tageswert?[y/n]");

                if (Repetitions.choice()){

                    Repetitions.CheckCalories(connect,localDate,tupperCalories);

                }
                System.out.println("Den Rest in die Tupper?");
                choice = scanner.next();
                if (choice.equals("y")) {

                    Insert.insertTupper(connect.connect(), localDate, counter, fullweight, tupperweight);

                }*/
          /*

        label.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    textField.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    button.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        *     ListSelectionModel cellSelectionModel = table.getSelectionModel();
    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        String selectedData = null;

        int[] selectedRow = table.getSelectedRows();
        int[] selectedColumns = table.getSelectedColumns();

        for (int i = 0; i < selectedRow.length; i++) {
          for (int j = 0; j < selectedColumns.length; j++) {
            selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
          }
        }
        System.out.println("Selected: " + selectedData);
      }

    });*/
}
