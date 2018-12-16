package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Registration extends JFrame {
    private JTextField F = new JTextField();
    private JTextField I = new JTextField();
    private JTextField O = new JTextField();
    private JTextField City = new JTextField();
    private JTextField Address = new JTextField();
    private JTextField Phone = new JTextField();
    private JTextField Email = new JTextField();
    private JButton зарегистрироватьсяButton = new JButton();
    private JPanel panel = new JPanel();
    private boolean isReady = false;

    public Registration(DataInputStream dis, DataOutputStream dos) {
        super("Registration");
        panel.add(F);
        panel.add(I);
        panel.add(O);
        panel.add(City);
        panel.add(Address);
        panel.add(Phone);
        panel.add(Email);
        panel.add(зарегистрироватьсяButton);
        setContentPane(panel);
        setSize(600, 800);
        setVisible(true);

        зарегистрироватьсяButton.addActionListener(e -> {
            try {
                dos.writeUTF("ADDC");
                dos.writeUTF(getInfo());
                JOptionPane.showMessageDialog(Registration.this, dis.readUTF(), "Result",
                        JOptionPane.INFORMATION_MESSAGE);
                new Operations(dis, dos);
                isReady = true;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        if(isReady)
            setVisible(false);
    }

    public String getInfo() {
        return F.getText() + " " + I.getText() + " " + O.getText() + "|" + City.getText() + " "
                + Address.getText() + ";" + Phone.getText() + ";" + Email.getText() + "|";
    }
}
