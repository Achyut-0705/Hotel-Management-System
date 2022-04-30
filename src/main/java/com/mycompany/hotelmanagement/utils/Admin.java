package main.java.com.mycompany.hotelmanagement.utils;

import com.mycompany.hotelmanagement.credentials.DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin {
    private String userName, password;
    
    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    public boolean isValid() {
        DataBase db = new DataBase("hotel");
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(db.getUrl(), db.userName, db.password);
            String query = "select * from hotel.admins where username=\"" + this.userName + "\" and password=\"" + this.password + "\"";
            Statement smt = conn.createStatement();
            ResultSet result = smt.executeQuery(query);
            return result.next();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public static void main(String args[]) {
        Admin obj = new Admin("Achyut", "admin");
        System.out.println(obj.isValid());
    }
}
