package Client;

import javax.swing.*;
import javax.swing.event.PopupMenuListener;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CHAB extends JFrame {
    private JComboBox comboBox1 = new JComboBox();
    private JTextField textField1 = new JTextField();
    private JTextField textField2 = new JTextField();
    private JTextField textField3 = new JTextField();
    private JButton вводButton = new JButton();
    private JPanel panel = new JPanel();
    private JPanel subpanel = new JPanel();
    private JLabel rub = new JLabel();
    private JLabel kop = new JLabel();
    private JLabel addpr = new JLabel();
    private JTextField textField4 = new JTextField();


    public CHAB(DataInputStream dis, DataOutputStream dos) {
        super("Change Book");
        panel.add(comboBox1);
        panel.add(textField1);
        panel.add(вводButton);
        panel.add(addpr);
        panel.add(textField4);
        subpanel.add(textField2);
        subpanel.add(textField3);
        subpanel.add(rub);
        subpanel.add(kop);
        panel.add(subpanel);
        setContentPane(panel);
        setContentPane(subpanel);
        setSize(800, 600);
        setVisible(true);

        if(comboBox1.getSelectedItem().equals("Цена")) {
            addpr.setEnabled(true);
            subpanel.setEnabled(true);
            textField2.setEnabled(true);
            textField3.setEnabled(true);
            rub.setEnabled(true);
            kop.setEnabled(true);
        }

        вводButton.addActionListener(e -> {
            try {
                dos.writeUTF("CHAB");
                //TODO: Тут, как и в СНАС и в СНЕВ, надо понять, что делать с кейзами - они не пашут.
                /*switch (comboBox1.getSelectedItem()) {
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
                }*/
                JOptionPane.showMessageDialog(CHAB.this, dis.readUTF(), "Result",
                        JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

    public String getInfo(String code) {
        if(comboBox1.getSelectedItem().equals("Цена"))
            return textField4.getText() + "|" + code + "|" + textField2.getText() + "." + textField3.getText();
        else
            return textField4.getText() + "|" + code + "|" + textField1.getText();
    }
}
