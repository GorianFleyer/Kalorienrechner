package com.Panel;

import com.con.Connect;
import com.functions.Repetitions;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BeerPanel extends JPanel {
    int localdate;
    Connect connect;
    JLabel label;
    JSlider jSlider;
    JButton beerbutton;
    int value;
    public BeerPanel(int localdate, Connect connect)
    {
        this.connect = connect;
        this.localdate = localdate;
        value = 0;
        label = new JLabel("Biermenge: " + value + "x 0.5l Bier");
        beerbutton = new JButton("Biere hinzufügen");
        beerbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            beerAction();
            }
        });
        jSlider = new JSlider(-10,20,value);
        jSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                value = jSlider.getValue();
                label.setText("Biermenge: " + value + "x 0.5l Bier");

            }
        });
        add(label);
        add(jSlider);
        add(beerbutton);

    }
    public void beerAction()
    {
        double beerCal = 0.0;
        beerCal = value * 215.0;
        JFrame frame = new JFrame();
        String[] options = new String[4];
        options[0] = new String("Eintragen");
        options[1] = new String("Nicht eintragen");
        options[2] = new String("Nur zählen");
        options[3] = new String("Promille berechnen");
        int dialog = JOptionPane.showOptionDialog(frame.getContentPane(),"Das wären "+beerCal+" Kalorien! Hinzufügen?","Beerzähler",
                0,JOptionPane.YES_NO_CANCEL_OPTION,null,options,null);
        if(dialog == 0)
            {
            try {
                Repetitions.CheckCalories(connect,localdate,beerCal);
                JOptionPane.showMessageDialog(null, "Ok");
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

            }
    }
}
