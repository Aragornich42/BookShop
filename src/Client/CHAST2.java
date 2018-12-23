package Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class CHAST2 extends JFrame {

    private JTextField textField1 = new JTextField();
    private JComboBox comboBox1;
    private JButton inpButton = new JButton("Ввод");
    private JPanel panel = new JPanel(null);
    private JLabel l1 = new JLabel("ФИО заказчика");
    private JLabel l2 = new JLabel("Новый статус");
    private Vector<String> items = new Vector<>();

    public CHAST2(DataInputStream dis, DataOutputStream dos, Socket server) {
        super("Change status");
        setLayout(new BorderLayout());
        setSize(700, 160);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        items.add("В обработке");
        items.add("Собран");
        items.add("Поставлен");
        comboBox1 = new JComboBox(items);

        l1.setBounds(15, 15, 150, 20);
        textField1.setBounds(170, 15, 500, 20);
        l2.setBounds(15, 50, 150, 20);
        comboBox1.setBounds(170, 50, 500, 20);
        inpButton.setBounds(15, 85, 660,20);
        panel.setBounds(0,0,685,100);

        panel.add(textField1);
        panel.add(comboBox1);
        panel.add(inpButton);
        panel.add(l1);
        panel.add(l2);
        setContentPane(panel);
        panel.setVisible(true);

        setVisible(true);

        inpButton.addActionListener(e -> {
            try {
                dos.writeUTF("CHAST");
                dos.writeUTF(getInfo());
                JOptionPane.showMessageDialog(new JFrame(), dis.readUTF(), "Result",
                        JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public String getInfo() {
        return textField1.getText() + "|" + comboBox1.getSelectedItem();
    }

}
