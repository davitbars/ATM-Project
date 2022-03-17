import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class menuScreen {
    private JFrame frame;
    private JPanel panel;

    menuScreen(){
        frame = new JFrame("MENU");
        panel = new JPanel();

        JButton deposit = new JButton("DEPOSIT");

        deposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new depositScreen();
            }
        });

        JButton withdraw = new JButton("WITHDRAW");
        withdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new withdrawScreen();
            }
        });

        JButton view = new JButton("VIEW BALANCE");
        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new viewScreen();
            }
        });

        JButton transfer = new JButton("WIRE TRANSFER");
        transfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new wireScreen();
            }
        });
        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new ATM();
            }
        });
        frame.setSize(600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setBackground(Color.BLACK);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        panel.add(deposit);
        panel.add(withdraw);
        panel.add(view);
        panel.add(transfer);
        panel.add(back);
        frame.setLayout( new GridBagLayout() );
    }
}
