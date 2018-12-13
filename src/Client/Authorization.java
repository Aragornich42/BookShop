package Client;

import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Authorization {
    private JTextField authField;
    private JButton authButton;
    private JButton registButton;
    private JLabel labelAuth;
    private JLabel labelReg;

    public Authorization() {
        authButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Operations oper = new Operations();
                try {
                    this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

        registButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registration reg = new Registration();
                try {
                    this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    public String getInfo() {
        return this.authField.getText();
    }

}
