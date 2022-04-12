package com.codegym.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/ProductManager","root","1994");
            System.out.println("ket noi thanh cong");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ket noi that bai");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
