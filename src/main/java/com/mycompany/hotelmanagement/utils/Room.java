package main.java.com.mycompany.hotelmanagement.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.hotelmanagement.credentials.DataBase;

public class Room implements Rooms{
    List<Guest> list;
    public int roomNumber;

    public Room(List<Guest> list) {
        this.list = list;
    }
    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room() {
        this.list = new ArrayList<>();
    }

    public Room(List<Guest> list, int roomNumber) {
        this.list = list;
        this.roomNumber = roomNumber;
    }

    @Override
    public void save(String checkin) {
        // save guest details in database
        DataBase db = new DataBase("hotel");
        String url = db.getUrl();
        String userName = db.userName;
        String password = db.password;
        Connection conn = null;
        Statement smt = null;

        try {

            conn = DriverManager.getConnection(url, userName, password);
            smt = conn.createStatement();
//            String query = "insert into hotel.rooms(roomNumber, isAvailable, checkin, checkout) values(" + this.roomNumber + ", \"false\", \"" + checkin + "\", \"NaN\");";
            String query =
                    "update hotel.rooms set isAvailable=\"false\", checkin=\"" + checkin + "\" where roomnumber=" + this.roomNumber;
            smt.execute(query);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                smt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isAvailable() {
        return list.size() == 0;
    }

    public List<Room> getAvailable() {
        List<Room> rooms = new ArrayList<>();
        DataBase db = new DataBase("hotel");
        String url = db.getUrl();
        String userName = db.userName;
        String password = db.password;
        Connection conn = null;
        Statement smt = null;

        try {
            conn = DriverManager.getConnection(url, userName, password);
            String query =
                    "select * from hotel.rooms where isAvailable=\"true\"";

            smt = conn.createStatement();
            ResultSet result = smt.executeQuery(query);
            while(result.next()) {
                Room obj = new Room(Integer.parseInt(result.getString(2)));
                rooms.add(obj);
//                System.out.println(result.getString(2));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rooms;
    }
    public List<Room> getBooked() {
        List<Room> rooms = new ArrayList<>();
        DataBase db = new DataBase("hotel");
        String url = db.getUrl();
        String userName = db.userName;
        String password = db.password;
        Connection conn = null;
        Statement smt = null;

        try {
            conn = DriverManager.getConnection(url, userName, password);
            String query =
                    "select * from hotel.rooms where isAvailable=\"false\"";

            smt = conn.createStatement();
            ResultSet result = smt.executeQuery(query);
            while(result.next()) {
                Room obj = new Room(Integer.parseInt(result.getString(2)));
                rooms.add(obj);
//                System.out.println(result.getString(2));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rooms;
    }

    public String checkout(String out) {
        DataBase db = new DataBase("hotel");
        String url = db.getUrl();
        String userName = db.userName;
        String password = db.password;
        Connection conn = null;
        Statement smt = null;
        String checkin = "";

        try {
            conn = DriverManager.getConnection(url, userName, password);
            smt = conn.createStatement();

            String query =
                    "update hotel.rooms set isAvailable=\"true\", checkout=\"" + out + "\" where roomnumber=" + this.roomNumber;

            smt.execute(query);
            query = "select checkin from hotel.rooms where roomnumber=" + this.roomNumber;
            ResultSet result = smt.executeQuery(query);
            while(result.next()) {
                checkin = result.getString(1);
                break;
            }

        } catch ( SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                smt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return checkin.trim().length() == 1 ? "0" + checkin : checkin;
    }

    public static void main(String[] args) {
        Room room = new Room(201);
        room.checkout("30 march 2022");
    }
}
