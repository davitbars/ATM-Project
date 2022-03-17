import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.sql.*;

public class viewScreen {
    
    private JFrame frame;
    private JPanel panel;
    private JLabel label;


    viewScreen(){
        String currUser = ATM.name.getText();
        try{
            Statement myStat = database.getConn().createStatement();
            String query = "SELECT * FROM mydb.atm_data WHERE Name='"+currUser+"'";
            ResultSet myRs = myStat.executeQuery(query);

            if(myRs.next()){
                Float bal = myRs.getFloat("Balance");
                NumberFormat myFormat = NumberFormat.getInstance();
                myFormat.setGroupingUsed(true);
                label = new JLabel(" Welcome " + currUser+ ", Your current account balance is: $"+ myFormat.format(bal)); 
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }         
        
        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new menuScreen();
            }
        });


        frame = new JFrame("VIEW BALANCE");
        panel = new JPanel();
        JPanel tpanel = new JPanel();
        frame.setSize(600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        frame.getContentPane().add(tpanel, BorderLayout.NORTH);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        tpanel.add(label);
        panel.add(back);
        
    }

}
