package Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class CHAC2 extends JFrame {

    private JComboBox comboBox1;
    private JTextField textField1 = new JTextField();
    private JButton inpButton = new JButton("Ввод");
    private JTextField textField2 = new JTextField();
    private JPanel panel = new JPanel(null);
    private JLabel l1 = new JLabel("Выберите тип данных, необходимый для изменения, и затем введите новое значение.");
    private JLabel l2 = new JLabel("Выберите тип данных");
    private JLabel l3 = new JLabel("Введите искомое");
    private JLabel l4 = new JLabel("Введите значение");
    private Vector<String> items = new Vector<>();

    public CHAC2(DataInputStream dis, DataOutputStream dos, Socket server) {
        super("Change customer");
        setLayout(new BorderLayout());
        setSize(700, 230);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        items.add("ФИО");
        items.add("Адрес");
        items.add("Телефон");
        items.add("email");
        items.add("Заказы(изменить целиком)");
        items.add("Заказы(добавить)");
        comboBox1 = new JComboBox(items);

        l1.setBounds(15, 15, 570, 20);
        l2.setBounds(15, 50, 150, 20);
        comboBox1.setBounds(170, 50, 500, 20);
        l3.setBounds(15, 85, 150,20);
        textField1.setBounds(170, 85, 500,20);
        l4.setBounds(15, 120, 150, 20);
        textField2.setBounds(170, 120, 500, 20);
        inpButton.setBounds(15, 155, 660, 20);
        panel.setBounds(0,0,685,170);

        panel.add(comboBox1);
        panel.add(textField1);
        panel.add(textField2);
        panel.add(inpButton);
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(l4);
        setContentPane(panel);
        panel.setVisible(true);

        setVisible(true);

        inpButton.addActionListener(e -> {
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
