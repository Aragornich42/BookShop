package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CHAST extends JFrame {
    private JTextField textField1 = new JTextField();
    private JComboBox comboBox1 = new JComboBox();
    private JButton вводButton = new JButton();
    private JPanel panel = new JPanel();

    public CHAST(DataInputStream dis, DataOutputStream dos) {
        super("Change status");
        panel.add(textField1);
        panel.add(comboBox1);
        panel.add(вводButton);
        setContentPane(panel);
        setSize(600, 300);
        setVisible(true);

        вводButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dos.writeUTF("CHAST");
                    dos.writeUTF(getInfo());
                    JOptionPane.showMessageDialog(CHAST.this, dis.readUTF(), "Result",
                            JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public String getInfo() {
        return textField1.getText() + "|" + comboBox1.getSelectedItem();
    }
}
