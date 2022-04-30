package com.mycompany.hotelmanagement.credentials;

public class DataBase {
    public String baseUrl = "jdbc:mysql://localhost:3306/";
    public String userName = "root";
    public String password = "admin@123";
    private String dbName;
    public DataBase(String dbName) {
        this.dbName = dbName;
    }
    
    public String getUrl() {
        return this.baseUrl + this.dbName;
    }
}
