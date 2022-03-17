import java.sql.*;

public class database {

    public static Connection getConn(){
        try{
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "DavoB2004");
            return myConn;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }  
    }
}
