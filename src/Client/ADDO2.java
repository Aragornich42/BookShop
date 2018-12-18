package Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ADDO2 extends JFrame {

    private JTextField textField1 = new JTextField();
    private JTextField textField2 = new JTextField();
    private JTextField textField3 = new JTextField();
    private JTextField textField4 = new JTextField();
    private JTextField textField5 = new JTextField();
    private JButton inpButton = new JButton("Ввод");
    private JPanel panel = new JPanel(null);
    private JLabel l1 = new JLabel("Название книги");
    private JLabel l2 = new JLabel("Автор");
    private JLabel l3 = new JLabel("Количество экземляров");
    private JLabel l4 = new JLabel("Удобное время получения");
    private JLabel l5 = new JLabel(":");
    private String fullName;
    private String address;

    public ADDO2(DataInputStream dis, DataOutputStream dos, String fullName, String address, Socket server) {
        super("Add order");
        setLayout(new BorderLayout());
        setSize(700, 230);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        l1.setBounds(15, 15, 150, 20);
        textField1.setBounds(170, 15, 500, 20);
        l2.setBounds(15, 50, 150, 20);
        textField2.setBounds(170, 50, 500, 20);
        l3.setBounds(15, 85, 150,20);
        textField3.setBounds(170, 85, 500,20);
        l4.setBounds(15, 120, 160, 20);
        textField4.setBounds(180, 120, 240, 20);
        l5.setBounds(423, 120, 4, 20);
        textField5.setBounds(430, 120, 240, 20);
        inpButton.setBounds(15, 155, 660, 20);
        panel.setBounds(0,0,685,170);

        panel.add(textField1);
        panel.add(textField2);
        panel.add(textField3);
        panel.add(textField4);
        panel.add(textField5);
        panel.add(inpButton);
        panel.add(l1);
        panel.add(l2);
        panel.add(l3);
        panel.add(l4);
        panel.add(l5);
        setContentPane(panel);
        panel.setVisible(true);

        setVisible(true);

        this.fullName = fullName;
        this.address = address;

        inpButton.addActionListener(e -> {
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
