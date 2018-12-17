package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CHEB extends JFrame {
    private JComboBox comboBox1 = new JComboBox();
    private JTextField textField1 = new JTextField();
    private JButton вводButton = new JButton();
    private JPanel panel = new JPanel();

    public CHEB(DataInputStream dis, DataOutputStream dos) {
        super("Check books");
        panel.add(comboBox1);
        panel.add(textField1);
        panel.add(вводButton);
        setContentPane(panel);
        setSize(600, 300);
        setVisible(true);

        вводButton.addActionListener(e -> {
            try {
                dos.writeUTF("CHEB");
                switch ((String) comboBox1.getSelectedItem()) {
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
