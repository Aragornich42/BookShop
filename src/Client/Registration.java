package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration {
    private JTextField F;
    private JTextField I;
    private JTextField O;
    private JTextField City;
    private JTextField Address;
    private JTextField Phone;
    private JTextField Email;

    private JButton зарегистрироватьсяButton;

    public Registration() {
        зарегистрироватьсяButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    public String getInfo() {
        return F.getText() + " " + I.getText() + " " + O.getText() + "|" + City.getText() + " "
                + Address.getText() + ";" + Phone.getText() + ";" + Email.getText();
    }
}
