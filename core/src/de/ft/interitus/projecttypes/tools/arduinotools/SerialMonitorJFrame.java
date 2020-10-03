package de.ft.interitus.projecttypes.tools.arduinotools;

import javax.swing.*;
import java.awt.*;

public class SerialMonitorJFrame extends JFrame {
    private JPanel panel1;
    private JTextArea CommunicationArea;


    public SerialMonitorJFrame(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1 = new JPanel(new BorderLayout());
        CommunicationArea = new JTextArea(30, 80);
        CommunicationArea.setEditable(true);
        CommunicationArea.setText("Hier steht irgendwann mal der vom Arduino Empfangene Text");
        JScrollPane detailScrollPane = new JScrollPane(CommunicationArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel1.add(detailScrollPane, BorderLayout.NORTH);

       // panel1.add(textArea1);
        this.setContentPane(panel1);
        this.pack();
    }



}
