package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoFrame extends JFrame {

    private JPanel panel = new JPanel(null);
    private JTextArea textArea = new JTextArea();
    private JButton button = new JButton("Закрыть окно");

    public  InfoFrame(String info) {
        super("InfoFrame");
        setLayout(new BorderLayout());
        setSize(645, 325);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        textArea.setBounds(15, 15, 600, 200);
        button.setBounds(15, 230, 600, 20);
        panel.setBounds(0, 0, 630, 265);

        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(textArea);
        panel.add(scrollPane);
        panel.add(button);
        setContentPane(panel);
        panel.setVisible(true);

        setVisible(true);

        textArea.setText(info);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }

}
