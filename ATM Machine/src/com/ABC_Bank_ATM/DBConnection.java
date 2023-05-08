package com.ABC_Bank_ATM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
    Connection con;
    Statement stmt;
    public DBConnection()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM_Machine", "root", "");
            //stmt=con.createStatement();
            System.out.println("connection successful");
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //new DBConnection();
    }
}
