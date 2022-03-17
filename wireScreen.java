import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class wireScreen {
    
    private JFrame frame;
    private JPanel panel;
    private JLabel jl = new JLabel();
    private JTextField jt = new JTextField("Enter amount to transfer", 15);
    private JTextField tranTo = new JTextField("Enter person to transfer to", 15);

    wireScreen(){
        
        String currUser = ATM.name.getText();  
        
        tranTo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tranTo.setText("");
            }
        });

        tranTo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {}});

        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jt.setText("");
            }
        });
        jt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {}});

        JButton confirm = new JButton("CONFIRM");
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try{
                    //pull current users data
                    //Statement myStat2 = database.getConn().createStatement();
                    Statement myStat = database.getConn().createStatement(); 
                    String query2 = "SELECT * FROM mydb.atm_data WHERE Name='"+currUser+"'";
                    ResultSet myRs2 = myStat.executeQuery(query2);

                    if (myRs2.next() && myRs2.getString("Name").equals("")){
                        JOptionPane.showMessageDialog(null, "This person does not bank with us", "Invalid account", 1);
                    }
                    if( myRs2.getString("Name").equals(currUser)){
                        JOptionPane.showMessageDialog(null, "You entered your own account", "Invalid account", 1);
                    }
            
                      // current user has the 2
                        Float myBal = myRs2.getFloat("Balance");
                        myBal -= Integer.parseInt(jt.getText());
                        String sql2 = "update mydb.atm_data "+ "set Balance = " + myBal + " where Name = '" + currUser+ "'";
                        myStat.executeUpdate(sql2);
                    

                    //pull data for who money goes to
                    
                    myStat.getMoreResults();
                    String query = "SELECT * FROM mydb.atm_data WHERE Name='"+tranTo.getText()+"'";
                    ResultSet myRs = myStat.executeQuery(query);

                    if (myRs.next()){   //money reciever  no 2
                        Float toBal = myRs.getFloat("Balance");
                        toBal += Integer.parseInt(jt.getText());
                        String sql = "update mydb.atm_data "+ "set Balance = " + toBal + " where Name = '" + tranTo.getText()  + "'";
                        myStat.executeUpdate(sql);
                        
                        JOptionPane.showMessageDialog(null, "Thank you for your time", "Transfer Complete", 1);
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

        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new menuScreen();
            }
        });


        frame = new JFrame("WIRE TRANSFER");
        panel = new JPanel();
        JPanel tpanel = new JPanel();
        JPanel bpanel = new JPanel();
        frame.setSize(600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.GREEN);
        
        JLabel prompt = new JLabel("How much would like to transfer and to who");
        

        frame.getContentPane().add(tpanel, BorderLayout.NORTH);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(bpanel, BorderLayout.SOUTH);
        tpanel.add(prompt);
        panel.add(jt);
        panel.add(tranTo);
        panel.add(back);
        bpanel.add(jl);
        bpanel.add(confirm);
        
        
        
    }
}


