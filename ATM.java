import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ATM{
    private JFrame frame;
    private JPanel panel;
    public static JTextField ID = new JTextField("Enter account number here", 15);
    public static JTextField name = new JTextField("Enter first Name Here", 15);
    private JLabel text = new JLabel("Welcome to Davos Bank. Please enter your account number and first name, then click start.");
    ATM(){
        
        ID.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ID.setText("");
            }
        });
        ID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String input = ID.getText();
                ID.setText(input);
                
            }
        });
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                name.setText("");
            }
        });
        name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String input = name.getText();
                name.setText(input);
                
            }
        });
        
        frame = new JFrame("ATM");
        panel = new JPanel();
        JPanel bpanel = new JPanel();
        

        JButton start  = new JButton("Click here to start");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                try{
                    Statement myStat = database.getConn().createStatement();
                    String query = "SELECT * FROM mydb.atm_data WHERE Account_Number='"+ID.getText()+"' and Name='"+name.getText()+"'";
                    ResultSet myRs = myStat.executeQuery(query);

                    if(myRs.next()){
                        frame.dispose();
                        new menuScreen();
                    }
                    else{
                        try {
                            Integer.parseInt(ID.getText()); 
                            JOptionPane.showMessageDialog(null,"ID does not match name","Invalid ID",1);
                        }
                        catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "No letters", "Input mismatch", 1);
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        //All layout and formatting stuff do not edit anymore
        frame.setSize(600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        panel.setBackground(Color.WHITE);
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(bpanel, BorderLayout.CENTER);
        panel.add(ID);
        panel.add(name);
        bpanel.add(text, BorderLayout.CENTER);
        bpanel.add(start, BorderLayout.SOUTH);
    }
    public static void main(String[] args){
        
        new ATM();
    }
}
