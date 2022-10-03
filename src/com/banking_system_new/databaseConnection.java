package com.banking_system_new;
import java.sql.*;

public class databaseConnection {
public Connection getConnection() throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","sunil@#$%$#@");
        return connection;
    }
}
