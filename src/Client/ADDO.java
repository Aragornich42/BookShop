package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ADDO extends JFrame {
    private JTextField textField1 = new JTextField();
    private JTextField textField2 = new JTextField();
    private JTextField textField3 = new JTextField();
    private JTextField textField4 = new JTextField();
    private JTextField textField5 = new JTextField();
    private JButton вводButton = new JButton();
    private JPanel panel = new JPanel();
    private String fullName;
    private String address;

    public ADDO(DataInputStream dis, DataOutputStream dos, String fullName, String address) {
        super("Add order");
        panel.add(textField1);
        panel.add(textField2);
        panel.add(textField3);
        panel.add(textField4);
        panel.add(textField5);
        panel.add(вводButton);
        setContentPane(panel);
        setSize(800, 600);
        setVisible(true);

        this.fullName = fullName;
        this.address = address;

        вводButton.addActionListener(e -> {
            try {
                dos.writeUTF("ADDO");
                dos.writeUTF(getInfo());
                dos.writeUTF(textField1.getText());
                dos.writeUTF(textField3.getText());
                JOptionPane.showMessageDialog(new JFrame(), dis.readUTF(), "Result",
                        JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public String getInfo() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String tmp = format.format(new Date());
        tmp += "|" + fullName + "|\"" + textField1.getText() + "\" " + textField2.getText() + ", "
                + textField3.getText() + " шт., " + "~" + "|" + address + "|" + textField4.getText()
                + ":" + textField5.getText() + "|В обработке";
        return tmp;
    }
}
