package de.ft.interitus.projecttypes.tools.arduinotools;

import javax.swing.*;
import java.awt.*;

public class SerialMonitorJFrame extends JFrame {
    private JPanel panel1;
    private JTextArea CommunicationArea;
    private JTextField sendStringInput;
    private JButton sendButton;

    public JScrollPane CommunicationAreaGenerate(){
        CommunicationArea = new JTextArea(30, 80);
        CommunicationArea.setEditable(true);
        CommunicationArea.setText("Hier steht irgendwann mal der vom Arduino Empfangene Text");
        JScrollPane detailScrollPane = new JScrollPane(CommunicationArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return detailScrollPane;
    }


    public SerialMonitorJFrame(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1 = new JPanel(new BorderLayout());

        panel1.add(CommunicationAreaGenerate(), BorderLayout.PAGE_START);

        sendStringInput= new JTextField("Der Text Der zum Arduino Gesendet Werden kann",30);
        panel1.add(sendStringInput, BorderLayout.CENTER);
        sendButton=new JButton("send");
        panel1.add(sendButton,BorderLayout.EAST);

        this.setContentPane(panel1);
        this.pack();
    }



}
