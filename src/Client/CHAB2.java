package Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class CHAB2 extends JFrame {

    Vector<String> items = new Vector<>();
    private JComboBox comboBox1;
    private JTextField textField1 = new JTextField();
    private JTextField textField2 = new JTextField();
    private JTextField textField3 = new JTextField();
    private JButton inpButton = new JButton("Ввод");
    private JPanel panel = new JPanel(null);
    private JLabel rub = new JLabel("руб.");
    private JLabel kop = new JLabel("коп.");
    private JLabel addpr = new JLabel("Введите цену");
    private JTextField textField4 = new JTextField();
    private JLabel l1 = new JLabel("Выберите тип данных");
    private JLabel l2 = new JLabel("Выберите название");
    private JLabel l3 = new JLabel("Введите значение");
    private JLabel head = new JLabel("Выберите тип данных, необходимый для изменения, и затем введите новое значение.");

    public CHAB2(DataInputStream dis, DataOutputStream dos, Socket server) {
        super("Change Book");
        setLayout(new BorderLayout());
        setSize(700, 265);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        items.add("Название");
        items.add("Автор(ы)");
        items.add("Издательство");
        items.add("Год издания");
        items.add("Количество страниц");
        items.add("Тип обложки");
        items.add("Цена");
        items.add("Количество");
        items.add("Жанр");
        comboBox1 = new JComboBox(items);

        head.setBounds(15, 15, 570, 20);
        l1.setBounds(15, 50, 150, 20);
        comboBox1.setBounds(170, 50, 500, 20);
        l2.setBounds(15, 85, 150,20);
        textField1.setBounds(170, 85, 500,20);
        l3.setBounds(15, 120, 150, 20);
        textField2.setBounds(170, 120, 500, 20);
        addpr.setBounds(15, 155, 150, 20);
        textField3.setBounds(170, 155, 225, 20);
        rub.setBounds(300, 155, 20, 20);
        textField4.setBounds(320, 155, 225, 20);
        kop.setBounds(550, 155, 20, 20);
        inpButton.setBounds(15, 190, 660, 20);
        panel.setBounds(0,0,685,225);

        panel.add(head);
        panel.add(comboBox1);
        panel.add(textField1);
        panel.add(inpButton);
        panel.add(addpr);
        panel.add(textField4);
        panel.add(textField2);
        panel.add(textField3);
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(rub);
        panel.add(kop);
        setContentPane(panel);
        panel.setVisible(true);

        setVisible(true);

        if(comboBox1.getSelectedItem().equals("Цена")) {
            addpr.setEnabled(true);
            textField2.setEnabled(true);
            textField3.setEnabled(true);
            rub.setEnabled(true);
            kop.setEnabled(true);
        }

        inpButton.addActionListener(e -> {
            try {
                dos.writeUTF("CHAB");
                String tmp = (String) comboBox1.getSelectedItem();
                switch (tmp) {
                    case "Название":
                        dos.writeUTF(getInfo("NAME"));
                        break;
                    case "Автор(ы)":
                        dos.writeUTF(getInfo("AUTHOR"));
                        break;
                    case "Издательство":
                        dos.writeUTF(getInfo("PUBLISH"));
                        break;
                    case "Год издания":
                        dos.writeUTF(getInfo("DATE"));
                        break;
                    case "Количество страниц":
                        dos.writeUTF(getInfo("PAGES"));
                        break;
                    case "Тип обложки":
                        dos.writeUTF(getInfo("COVER"));
                        break;
                    case "Цена":
                        dos.writeUTF(getInfo("PRICE"));
                        break;
                    case "Количество":
                        dos.writeUTF(getInfo("COUNT"));
                        break;
                    case "Жанр":
                        dos.writeUTF(getInfo("GENRE"));
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
        if(comboBox1.getSelectedItem().equals("Цена"))
            return textField1.getText() + "|" + code + "|" + textField3.getText() + "." + textField4.getText();
        else
            return textField1.getText() + "|" + code + "|" + textField2.getText();
    }

}
