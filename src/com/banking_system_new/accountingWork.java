package com.banking_system_new;

import java.util.*;
import java.sql.*;

public class accountingWork {
    public void createAccount() throws Exception {
        int n = 0, an = 0, pin = 0;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Please fill up the Form!!!");
        Connection connection = new databaseConnection().getConnection();
        Statement statement = connection.createStatement();
        String query = ("select AccountNumber from bankingdetails");
        ResultSet resultSet = statement.executeQuery(query);
        label:
        do {
            n = random.nextInt(100000) + 100000;
            while (resultSet.next()) {
                an = resultSet.getInt("AccountNumber");
                if (an == n)
                    continue label;
            }
        } while (an == n);
        System.out.println("The Account Number assigned to you is: " + n);
        System.out.println("Enter your First Name: ");
        String fname = scanner.nextLine();
        System.out.println("Enter your Last Name: ");
        String lname = scanner.nextLine();
        System.out.println("Enter your Phone Number: ");
        String pnum = scanner.nextLine();
        System.out.println("Enter your Address: ");
        String add = scanner.nextLine();
        System.out.println("Create your four digits pin: ");
        pin = scanner.nextInt();
        String sql = "INSERT INTO bankingdetails (AccountNumber,FirstName,LastName,PhoneNumber,Address,ATM_PIN) VALUES(" + n + ",'" + fname + "','" + lname + "','" + pnum + "','" + add + "'," + pin + ")";
        statement.executeUpdate(sql);
    }

    public void Deposit() throws Exception {
        int y = 0, t, d, i = 1;
        Connection connection = new databaseConnection().getConnection();
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter your account number:");
            int n = scanner.nextInt();
            String query = "select * from bankingdetails";
            ResultSet rs = statement.executeQuery(query);
            Boolean hasNext = rs.next();
            while (hasNext) {
                int an = rs.getInt("AccountNumber");
                if (an == n) {
                    System.out.println("Enter the Deposit Amount:");
                    d = scanner.nextInt();
                    query = "select * from bankingdetails where AccountNumber=" + an + "";
                    rs = statement.executeQuery(query);
                    rs.next();
                    t = rs.getInt("Balance");
                    y = t + d;
                    String sql = "update bankingdetails set Balance=" + y + ",Latest_Deposit_Amount=" + d + " where AccountNumber=" + n + "";
                    statement.executeUpdate(sql);
                    i = 4;
                    break;

                } else {
                    hasNext = rs.next();
                    if (!hasNext) {
                        i++;
                        System.out.println("Wrong account number.Try again.");
                    }
                    if (i == 4)
                        System.out.println("Reached maximum time.");
                }
            }
        } while (i < 4);
    }
}
