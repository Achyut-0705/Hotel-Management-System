package main.java.com.mycompany.hotelmanagement.utils;

import com.mycompany.hotelmanagement.credentials.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Guest extends Person{
    String roomNumber;

    public Guest() {
        roomNumber = "";
    }

    public Guest(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Guest(String name, String phn, String roomNumber) {
        super(name, phn);
        this.roomNumber = roomNumber;
    }

    public void save() {
        Connection conn = null;
        Statement smt = null;
        DataBase db = new DataBase("hotel");
        String url = db.getUrl();
        String userName = db.userName;
        String password = db.password;
        try {
            conn = DriverManager.getConnection(url, userName, password);
            smt = conn.createStatement();
            String query =
                    "insert into hotel.guests(guestName, guestPhn, roomNo) values(\"" + name + "\", \"" + phn + "\", \"" + roomNumber + "\");" ;
            smt.execute(query);

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                smt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
