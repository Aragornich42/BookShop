package Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Operations2 extends JFrame {

    private JButton ADDO = new JButton("Добавить заказ");
    private JButton ADDB = new JButton("Добавить книгу");
    private JButton DELB = new JButton("Удалить книгу");
    private JButton DELC = new JButton("Удалить пользователя");
    private JButton CHAB = new JButton("Изменить данные по книге");
    private JButton CHAC = new JButton("Изменить данные по клиенту");
    private JButton CHEST = new JButton("Проверить статус заказа");
    private JButton CHAST = new JButton("Изменить статус заказа");
    private JButton CHEB = new JButton("Получить список книг");
    private JButton END = new JButton("Завершить работу");
    private JPanel panel = new JPanel(null);

    public Operations2(DataInputStream dis, DataOutputStream dos, String fullName, String address, Socket server) {
        super("Operations");
        setLayout(new BorderLayout());
        setSize(545, 405);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ADDO.setBounds(15, 15, 500, 20);
        ADDB.setBounds(15, 50, 500, 20);
        DELB.setBounds(15, 85, 500,20);
        DELC.setBounds(15, 120, 500, 20);
        CHAB.setBounds(15, 155, 500, 20);
        CHAC.setBounds(15, 190, 500, 20);
        CHEST.setBounds(15, 225, 500, 20);
        CHAST.setBounds(15, 260, 500, 20);
        CHEB.setBounds(15, 295, 500, 20);
        END.setBounds(15, 330, 500, 20);
        panel.setBounds(0,0, 530, 345);

        panel.add(ADDO);
        panel.add(ADDB);
        panel.add(DELB);
        panel.add(DELC);
        panel.add(CHAB);
        panel.add(CHAC);
        panel.add(CHEST);
        panel.add(CHAST);
        panel.add(CHEB);
        panel.add(END);
        setContentPane(panel);
        panel.setVisible(true);

        setVisible(true);

        ADDO.addActionListener(e -> new ADDO2(dis, dos, fullName, address, server));

        ADDB.addActionListener(e -> new ADDB2(dis, dos, server));

        DELB.addActionListener(e -> {
            String tmp = JOptionPane.showInputDialog(new JFrame(), "Введите название книги:");
            while(true) {
                if (tmp == null) {
                    tmp = JOptionPane.showInputDialog(new JFrame(), "Вы не ввели название книги. Введите его:");
                } else {
                    break;
                }
            }
            try {
                dos.writeUTF("DELB");
                dos.writeUTF(tmp);
                JOptionPane.showMessageDialog(new JFrame(), dis.readUTF(), "Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        DELC.addActionListener(e -> {
            String tmp = JOptionPane.showInputDialog(new JFrame(), "Введите ФИО клиента:");
            while(true) {
                if (tmp == null) {
                    tmp = JOptionPane.showInputDialog(new JFrame(), "Вы не ввели ФИО. Введите его:");
                } else {
                    break;
                }
            }
            try {
                dos.writeUTF("DELC");
                dos.writeUTF(tmp);
                JOptionPane.showMessageDialog(new JFrame(), dis.readUTF(), "Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        CHAB.addActionListener(e -> new CHAB2(dis, dos, server));

        CHAC.addActionListener(e -> new CHAC2(dis, dos, server));

        CHEST.addActionListener(e -> {
            String tmp = JOptionPane.showInputDialog(new JFrame(), "Введите ФИО заказчика:");
            while(true) {
                if (tmp == null) {
                    tmp = JOptionPane.showInputDialog(new JFrame(), "Вы не ввели ФИО. Введите его:");
                } else {
                    break;
                }
            }
            try {
                dos.writeUTF("CHEST");
                dos.writeUTF(tmp);
                JOptionPane.showMessageDialog(new JFrame(), dis.readUTF(), "Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        CHAST.addActionListener(e -> new CHAST2(dis, dos, server));

        CHEB.addActionListener(e -> new CHEB2(dis, dos, server));

        END.addActionListener(e -> {
            try {
                dos.writeUTF("END");
                dis.close();
                dos.close();
                server.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            setVisible(false);
        });

    }

}
