import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.*;

public class depositScreen {
    
    private JFrame frame;
    private JPanel panel;
    private JLabel jl = new JLabel();
    private JTextField jt = new JTextField("00", 30);
    JLabel prompt = new JLabel("How much would like to deposit\n");

    depositScreen(){
        String currUser = ATM.name.getText();
        jt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jt.setText("");
            }
        });
        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new menuScreen();
            }
        });

        JButton confirm = new JButton("CONFIRM");
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try{
                    Statement myStat = database.getConn().createStatement();
                    String query = "SELECT * FROM mydb.atm_data WHERE Name='"+currUser+"'";
                    ResultSet myRs = myStat.executeQuery(query);

                    if (myRs.next()){
                        Float startBal = myRs.getFloat("Balance");
                        Float wAmount = Float.valueOf(jt.getText()).floatValue();
                        Float newBal = startBal + wAmount;
                        String sql = "update mydb.atm_data "+ "set Balance = " + newBal + " where Name = '" + currUser + "'";
                                    
                                    
                        myStat.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Thank you for your time", "Deposit Complete", 1);
                        new viewScreen();
                        
                    }
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
                catch(NumberFormatException n){
                    JOptionPane.showMessageDialog(null, "No letters", "Input mismatch", 1);
                }
            }
        });

        frame = new JFrame("DEPOSIT");
        panel = new JPanel();
        JPanel tpanel = new JPanel();
        JPanel bpanel = new JPanel();

        frame.setSize(600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        frame.getContentPane().add(tpanel, BorderLayout.NORTH);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(bpanel, BorderLayout.SOUTH);

        tpanel.add(prompt);
        
        bpanel.add(jl);
        bpanel.add(confirm);
        panel.add(jt);
        panel.add(back);
        
        
        
    }
}
