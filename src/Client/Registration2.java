package Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Registration2 extends JFrame {

    private JTextField F = new JTextField();
    private JTextField I = new JTextField();
    private JTextField O = new JTextField();
    private JTextField City = new JTextField();
    private JTextField Address = new JTextField();
    private JTextField Phone = new JTextField();
    private JTextField Email = new JTextField();
    private JButton regButton = new JButton("Зарегистрироваться");
    private JPanel panel = new JPanel(null);
    private JLabel Fl = new JLabel("Введите фамилию");
    private JLabel Il = new JLabel("Введите имя");
    private JLabel Ol = new JLabel("Введите отчество");
    private JLabel Cityl = new JLabel("Введите город");
    private JLabel Addressl = new JLabel("Введите адрес");
    private JLabel Phonel = new JLabel("Введите телефон");
    private JLabel Emaill = new JLabel("Введите email");

    public Registration2(DataInputStream dis, DataOutputStream dos, Socket server) {
        super("Registration");
        setLayout(new BorderLayout());
        setSize(700,355);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Fl.setBounds(15, 15, 150, 20);
        F.setBounds(170, 15, 500, 20);
        Il.setBounds(15, 50, 150, 20);
        I.setBounds(170, 50, 500, 20);
        Ol.setBounds(15, 85, 150,20);
        O.setBounds(170, 85, 500,20);
        Cityl.setBounds(15, 120, 150, 20);
        City.setBounds(170, 120, 500, 20);
        Addressl.setBounds(15, 155, 150, 20);
        Address.setBounds(170, 155, 500, 20);
        Phonel.setBounds(15, 190, 150, 20);
        Phone.setBounds(170, 190, 500, 20);
        Emaill.setBounds(15, 225, 150, 20);
        Email.setBounds(170, 225, 500, 20);
        regButton.setBounds(15, 260, 660, 20);
        panel.setBounds(0,0, 685, 295);

        panel.add(F);
        panel.add(I);
        panel.add(O);
        panel.add(City);
        panel.add(Address);
        panel.add(Phone);
        panel.add(Email);
        panel.add(regButton);
        panel.add(Fl);
        panel.add(Il);
        panel.add(Ol);
        panel.add(Cityl);
        panel.add(Addressl);
        panel.add(Phonel);
        panel.add(Emaill);
        setContentPane(panel);
        panel.setVisible(true);

        setVisible(true);

        regButton.addActionListener(e -> {
            try {
                dos.writeUTF("ADDC");
                dos.writeUTF(getInfo());
                JOptionPane.showMessageDialog(new JFrame(), dis.readUTF(), "Result",
                        JOptionPane.INFORMATION_MESSAGE);
                new Operations2(dis, dos, F.getText() + " " + I.getText() + " " + O.getText(),
                        Address.getText(), server);
                setVisible(false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public String getInfo() {
        return F.getText() + " " + I.getText() + " " + O.getText() + "|" + City.getText() + " "
                + Address.getText() + ";" + Phone.getText() + ";" + Email.getText() + "| ";
    }

}
