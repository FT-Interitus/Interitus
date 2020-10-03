package de.ft.interitus.projecttypes.tools.arduinotools;

import com.badlogic.gdx.Files;
import com.bulenkov.darcula.DarculaLaf;
import de.ft.interitus.loading.AssetLoader;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;

public class SerialMonitorJFrame extends JFrame {
    private JPanel panel1;
    private JTextArea CommunicationArea;
    private JTextField sendStringInput;
    private JButton sendButton;

    public JScrollPane CommunicationAreaGenerate(){
        CommunicationArea = new JTextArea(32, 80);
        CommunicationArea.setEditable(false);
        CommunicationArea.setText("Hier steht irgendwann mal der vom Arduino Empfangene Text");
        JScrollPane detailScrollPane = new JScrollPane(CommunicationArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return detailScrollPane;
    }


    public SerialMonitorJFrame(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            ImageIcon img = new ImageIcon(getClass().getResource("/Icon/interitus.png"));
            super.setIconImage(img.getImage());
            //this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
            this.setResizable(false);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            UIManager.setLookAndFeel(new DarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

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
