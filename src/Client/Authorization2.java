package Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Authorization2 extends JFrame {

    private JTextField authField = new JTextField();
    private JButton authButton = new JButton("Вход");
    private JButton registButton = new JButton("Регистрация");
    private JLabel labelAuth = new JLabel("Введите email для входа, пожалуйста:");
    private JLabel labelReg = new JLabel("Если Вы здесь впервые, зарегистрируйтесь:");
    private JPanel authFrame = new JPanel(null);
    private String fullName;
    private String address;
    private Socket server = new Socket("localhost", 8080);
    private DataInputStream dis = new DataInputStream(server.getInputStream());
    private DataOutputStream dos = new DataOutputStream(server.getOutputStream());

    public Authorization2() throws IOException {
        super("Authorization");

        setLayout(new BorderLayout());
        setSize(545, 230);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        labelAuth.setBounds(15, 15, 500, 20);
        authField.setBounds(15, 50, 500, 20);
        authButton.setBounds(15, 85, 500,20);
        labelReg.setBounds(15, 120, 500, 20);
        registButton.setBounds(15, 155, 500, 20);
        authFrame.setBounds(0,0, 530, 170);

        authFrame.add(authField);
        authFrame.add(authButton);
        authFrame.add(registButton);
        authFrame.add(labelAuth);
        authFrame.add(labelReg);
        setContentPane(authFrame);
        authFrame.setVisible(true);

        setVisible(true);

        authButton.addActionListener(e -> {
            try {
                dos.writeUTF("CHEC");
                dos.writeUTF(authField.getText());
                if(dis.readUTF().equals("User found")) {
                    fullName = dis.readUTF();
                    address = dis.readUTF();
                    new Operations2(dis, dos, fullName, address, server);
                    setVisible(false);
                }
                else if(dis.readUTF().equals("User not found"))
                    JOptionPane.showMessageDialog(new JFrame(),
                            "email некорректен, введите его заново, или зарегистрируйтесь", "Result",
                            JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        registButton.addActionListener(e -> {
            new Registration2(dis, dos, server);
            setVisible(false);
        });
    }

}
