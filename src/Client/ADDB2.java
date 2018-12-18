package Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ADDB2 extends JFrame {

    private JTextField textField1 = new JTextField();
    private JTextField textField2 = new JTextField();
    private JTextField textField3 = new JTextField();
    private JTextField textField4 = new JTextField();
    private JTextField textField5 = new JTextField();
    private JTextField textField6 = new JTextField();
    private JTextField textField7 = new JTextField();
    private JTextField textField8 = new JTextField();
    private JTextField textField9 = new JTextField();
    private JTextField textField10 = new JTextField();
    private JPanel panel = new JPanel(null);
    private JButton button2 = new JButton("Ввод");
    private JLabel l1 = new JLabel("Название");
    private JLabel l2 = new JLabel("Автор(ы)");
    private JLabel l3 = new JLabel("Жанр");
    private JLabel l4 = new JLabel("Издательство");
    private JLabel l5 = new JLabel("Год издания");
    private JLabel l6 = new JLabel("Количество страниц");
    private JLabel l7 = new JLabel("Тип обложки");
    private JLabel l8 = new JLabel("Количество");
    private JLabel l9 = new JLabel("Цена");
    private JLabel l10 = new JLabel("руб.");
    private JLabel l11 = new JLabel("коп.");

    public ADDB2(DataInputStream dis, DataOutputStream dos, Socket server) {
        super("Add book");
        setLayout(new BorderLayout());
        setSize(700, 405);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        l1.setBounds(15, 15, 150, 20);
        textField1.setBounds(170, 15, 500, 20);
        l2.setBounds(15, 50, 150, 20);
        textField2.setBounds(170, 50, 500, 20);
        l3.setBounds(15, 85, 150,20);
        textField3.setBounds(170, 85, 500,20);
        l4.setBounds(15, 120, 150, 20);
        textField4.setBounds(170, 120, 500, 20);
        l5.setBounds(15, 155, 150, 20);
        textField5.setBounds(170, 155, 500, 20);
        l6.setBounds(15, 190, 150, 20);
        textField6.setBounds(170, 190, 500, 20);
        l7.setBounds(15, 225, 150, 20);
        textField7.setBounds(170, 225, 500, 20);
        l9.setBounds(15, 260, 150, 20);
        textField9.setBounds(170, 260, 215, 20);
        l10.setBounds(290, 260, 30, 20);
        textField10.setBounds(330, 260, 225, 20);
        l11.setBounds(530, 260, 30, 20);
        l8.setBounds(15, 295, 150, 20);
        textField8.setBounds(170, 295, 500, 20);
        button2.setBounds(15, 330, 660, 20);
        panel.setBounds(0,0, 685, 345);

        panel.add(textField1);
        panel.add(textField2);
        panel.add(textField3);
        panel.add(textField4);
        panel.add(textField5);
        panel.add(textField6);
        panel.add(textField7);
        panel.add(textField8);
        panel.add(textField9);
        panel.add(textField10);
        panel.add(button2);
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(l4);
        panel.add(l5);
        panel.add(l6);
        panel.add(l7);
        panel.add(l8);
        panel.add(l9);
        panel.add(l10);
        panel.add(l11);

        setContentPane(panel);
        panel.setVisible(true);

        setVisible(true);

        button2.addActionListener(e -> {
            try {
                dos.writeUTF("ADDB");
                dos.writeUTF(getText());
                JOptionPane.showMessageDialog(new JFrame(), dis.readUTF(), "Result",
                        JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public String getText() {
        return textField1.getText() + "|" + textField2.getText() + "|" + textField3.getText() + "|"
                + textField4.getText() + "," + textField5.getText() + "," + textField6.getText() + ","
                + textField7.getText() + "|" + textField8.getText() + "." + textField9.getText() + "|"
                + textField10.getText();
    }

}
