package Client;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Authorization extends JFrame {
    private JTextField authField/* = new JTextField()*/;
    private JButton authButton /*= new JButton()*/;
    private JButton registButton /*= new JButton()*/;
    private JLabel labelAuth/* = new JLabel()*/;
    private JLabel labelReg/* = new JLabel()*/;
    private JPanel authFrame/* = new JPanel()*/;
    boolean isReady = false;
    private String fullName;
    private String address;

    public Authorization(DataInputStream dis, DataOutputStream dos) {
        super("Authorization");
        /*authFrame.add(authField);
        authFrame.add(authButton);
        authFrame.add(registButton);
        authFrame.add(labelAuth);
        authFrame.add(labelReg);*/
        setContentPane(authFrame);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 300);
        pack();
        setVisible(true);

        authButton.addActionListener(e -> {
            try {
                dos.writeUTF("CHEC");
                dos.writeUTF(authField.getText());
                if(dis.readUTF().equals("User found")) {
                    fullName = dis.readUTF();
                    address = dis.readUTF();
                    new Operations(dis, dos, fullName, address);
                    isReady = true;
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
            new Registration(dis, dos);
            isReady = true;
        });

        if(isReady) {
            setVisible(false);
        }

    }

}
