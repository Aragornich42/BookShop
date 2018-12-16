package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ADDO extends JFrame {
    private JTextField textField1 = new JTextField();
    private JTextField textField2 = new JTextField();
    private JTextField textField3 = new JTextField();
    private JTextField textField4 = new JTextField();
    private JTextField textField5 = new JTextField();
    private JButton вводButton = new JButton();
    private JPanel panel = new JPanel();

    public ADDO(DataInputStream dis, DataOutputStream dos) {
        super("Add order");
        panel.add(textField1);
        panel.add(textField2);
        panel.add(textField3);
        panel.add(textField4);
        panel.add(textField5);
        panel.add(вводButton);
        setContentPane(panel);
        setSize(800, 600);
        setVisible(true);
        //TODO: Реализовать возможность узнавать инфу по заказчику. Как вариант - пересылать ее с пом. конструкторов.
        вводButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
