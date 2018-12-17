package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ADDB extends JFrame {
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
    private JPanel panel;
    private JButton button2;

    public ADDB(DataInputStream dis, DataOutputStream dos) {
        super("Add book");
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
        setContentPane(panel);
        setSize(600, 1000);
        setVisible(true);

        button2.addActionListener(e -> {
            try {
                dos.writeUTF("ADDB");
                dos.writeUTF(getText());
                JOptionPane.showMessageDialog(new JFrame(), dis.readUTF(), "Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        setVisible(false);
    }

    public String getText() {
        return textField1.getText() + "|" + textField2.getText() + "|" + textField3.getText() + "|"
                + textField4.getText() + "," + textField5.getText() + "," + textField6.getText() + ","
                + textField7.getText() + "|" + textField8.getText() + "." + textField9.getText() + "|"
                + textField10.getText();
    }
}
