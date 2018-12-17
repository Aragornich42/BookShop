package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CHAC extends JFrame {
    private JComboBox comboBox1 = new JComboBox();
    private JTextField textField1 = new JTextField();
    private JButton вводButton = new JButton();
    private JTextField textField2 = new JTextField();
    private JPanel panel = new JPanel();

    public CHAC(DataInputStream dis, DataOutputStream dos) {
        super("Change customer");
        panel.add(comboBox1);
        panel.add(textField1);
        panel.add(textField2);
        panel.add(вводButton);
        setContentPane(panel);
        setSize(600, 400);
        setVisible(true);

        вводButton.addActionListener(e -> {
            try {
                dos.writeUTF("CHAC");
                switch ((String) comboBox1.getSelectedItem()) {
                    case "ФИО":
                        dos.writeUTF(getInfo("NAME"));
                        break;
                    case "Адрес":
                        dos.writeUTF(getInfo("ADDRESS"));
                        break;
                    case "Телефон":
                        dos.writeUTF(getInfo("PHONE"));
                        break;
                    case "email":
                        dos.writeUTF(getInfo("EMAIL"));
                        break;
                    case "Заказы(изменить целиком)":
                        dos.writeUTF(getInfo("ORDERS"));
                        break;
                    case "Заказы(добавить)":
                        dos.writeUTF(getInfo("COVER"));
                        break;
                }
                JOptionPane.showMessageDialog(new JFrame(), dis.readUTF(), "Result",
                        JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public String getInfo(String code) {
        return textField2.getText() + "|" + code + "|" + textField1.getText();
    }
}
