package com.Panel;

import com.con.Connect;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class NotePanel extends JPanel {

    int localdate;
    Connect connect;
    JTextArea notePad;

    public NotePanel(int localdate, Connect connect) {
        this.connect = connect;
        this.localdate = localdate;
        notePad = new JTextArea();
        notePad.getText();
        notePad.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {

            }
        });
//@Todo: Die Methode hat ein Get-Text. Entweder muss ich die Notizen an der Stelle auslesen per String-Array oder die Länge wird limitiert
        // oder die Zeilen müssen einzeln ausgelesen werden.

    }
}
