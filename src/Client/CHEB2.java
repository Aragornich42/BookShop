package Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class CHEB2 extends JFrame {

    private JComboBox comboBox1;
    private JTextField textField1 = new JTextField();
    private JButton inpButton = new JButton("Ввод");
    private JPanel panel = new JPanel(null);
    private JLabel l1 = new JLabel("Выберите тип данных, необходимый для поиска, и затем введите значение для поиска.");
    private JLabel l2 = new JLabel("Выберите тип данных");
    private JLabel l3 = new JLabel("Введите значение");
    private Vector<String> items = new Vector<>();

    public CHEB2(DataInputStream dis, DataOutputStream dos, Socket server) {
        super("Check books");
        setLayout(new BorderLayout());
        setSize(700, 195);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        items.add("id");
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

        l1.setBounds(15, 15, 570, 20);
        l2.setBounds(15, 50, 150, 20);
        comboBox1.setBounds(170, 50, 500, 20);
        l3.setBounds(15, 85, 150,20);
        textField1.setBounds(170, 85, 500,20);
        inpButton.setBounds(15, 120, 660, 20);
        panel.setBounds(0,0,685,135);

        panel.add(comboBox1);
        panel.add(textField1);
        panel.add(inpButton);
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        setContentPane(panel);
        panel.setVisible(true);

        setVisible(true);

        inpButton.addActionListener(e -> {
            try {
                dos.writeUTF("CHEB");
                switch ((String) comboBox1.getSelectedItem()) {
                    case "id":
                        dos.writeUTF(getInfo("ID"));
                        break;
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
        return code + "|" + textField1.getText();
    }

}
