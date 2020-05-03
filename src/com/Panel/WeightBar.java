package com.Panel;

import com.con.Connect;
import com.con.Insert;
import com.con.Select;
import com.con.Update;
import com.functions.DateCalc;
import jdk.jfr.Percentage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DecimalFormat;

public class WeightBar extends JPanel {
    Connect connect;
    int localDate;
    JLabel label;
    JButton button;
    JTextField textField;
    public WeightBar(int localdate, Connect connect)
    {
        this.connect = connect;
        this.localDate = localdate;
        setLayout(new FlowLayout());
        /*final String msg = num > 10
  ? "Number is greater than 10"
  : "Number is less than or equal to 10";*/
       final String labeltext = !weightCheck()
              ? "Gewicht am " + DateCalc.GermanDate(localDate) + "noch nicht gesetzt."
               : "Gewicht für " + DateCalc.GermanDate(localDate) + " wurde schon gesetzt.";
        final String buttonText = !weightCheck()
                ?"Gewicht eintragen"
                :"Gewicht updaten";
        String textFieldText = !weightCheck()
        ?""
         : Select.SelectFromDayWeight(connect.connect()).get(localDate).toString();
  /*      if(weightCheck())
        {
            labeltext = "Gewicht für " + DateCalc.GermanDate(localDate) + " wurde schon gesetzt.";
            buttonText = "Gewicht updaten";
            textFieldText = Select.SelectFromDayWeight(connect.connect()).get(localDate).toString();
        }*/

        label = new JLabel(labeltext);
        button = new JButton(buttonText);
        textField = new JTextField(textFieldText,8);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ButtonAction();
            }
        });


        /*         double input;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Wie viel Kilogramm haben Sie am " + DateCalc.GermanDate(localDate) +" auf der Waage gesehen?");
        System.out.print("Gewicht: ");
        input = scanner.nextDouble();
        try {
            if (!Select.SelectFromDayWeight(connect.connect()).containsKey(localDate)) {
                Insert.insertDayWeight(connect.connect(), localDate, input);
            } else {
                System.out.println("gibt's schon");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


*/
        add(label);
        add(textField);
        add(button);
    }

    public boolean weightCheck()
    {
        return Select.SelectFromDayWeight(connect.connect()).containsKey(localDate);
    }

    public void ButtonAction()
    {
        if(textField.getText().isBlank() || textField.getText().isEmpty())
        {
          JOptionPane.showMessageDialog(this, "Das Feld darf nicht leer sein");

        }
        else
        {
            if(!textField.getText().matches("\\d*\\.*\\d*"))
            {
                JOptionPane.showMessageDialog(this, "Das Feld enthält keine nummer");
            }
            else
            {
                try
                {
                    double c = Double.parseDouble(textField.getText());
                    JOptionPane.showMessageDialog(this, c);
                    if(!weightCheck())
                    {
                        Insert.insertDayWeight(connect.connect(),localDate,c);
                        this.repaint();
                    }
                    else
                    {
                        Update.UpdateDayWeight(connect.connect(),localDate,c);
                    }
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }



        }
    }



}
