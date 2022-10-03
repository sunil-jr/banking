package com.banking_system_new;
import java.util.*;
import java.sql.*;
public class atmWork
{
        public void atmWorking()throws Exception
        {
            int choice;
            Scanner scanner=new Scanner(System.in);
            String query="select * from bankingdetails";
            Connection cn= new databaseConnection().getConnection();
            Statement stat=cn.createStatement();
            System.out.println("Enter your account number:");
            int n1=scanner.nextInt();
            ResultSet RS=stat.executeQuery(query);
            Boolean hasNext=RS.next();
            while(hasNext)
            {

                int an=RS.getInt("AccountNumber");
                if(an==n1)
                {
                    do
                    {
                        System.out.println("\n\n\n\nWELCOME TO ATM SERVICE!");
                        System.out.println("\n\nWhat you want to do?\tChoose a Number");
                        System.out.println("1. Cash Withdrawl\t\t\n2.Balance Inquiry\t\t\n3.Statement\t\t\n4.Exit\t\t\t\n\n");
                        System.out.println("Enter your choice:");
                        choice=scanner.nextInt();
                        switch(choice)
                        {
                            case 1:
                            {
                                int pin=0,apin=0,b=0,w=0;
                                System.out.println("Enter PIN NUMBER:");
                                pin=scanner.nextInt();
                                query="select * from bankingdetails where AccountNumber="+an+"";
                                ResultSet rs=stat.executeQuery(query);
                                while(rs.next())
                                {
                                    apin=rs.getInt("ATM_PIN");
                                    b=rs.getInt("Balance");
                                }
                                if(pin!=apin)
                                    System.out.println("Incorrect PIN!!!");
                                else
                                {
                                    System.out.println("How much amount to withdraw?");
                                    w=scanner.nextInt();
                                    if(w<b)
                                    {
                                        b=b-w;
                                        String sql="update bankingdetails set Balance="+b+",Latest_Withdrawn_Amount="+w+" where ATM_PIN="+pin+"";
                                        stat.executeUpdate(sql);
                                    }
                                }
                                break;
                            }
                            case 2:
                            {
                                int pin=0,apin=0,b=0;
                                System.out.println("Enter PIN NUMBER:");
                                pin=scanner.nextInt();
                                query="select * from bankingdetails where AccountNumber="+an+"";
                                ResultSet rs=stat.executeQuery(query);
                                while(rs.next())
                                {
                                    apin=rs.getInt("ATM_PIN");
                                    b=rs.getInt("Balance");
                                }
                                if(pin!=apin)
                                    System.out.println("Incorrect PIN!!!");
                                else
                                {
                                    System.out.println("Your current balance is:"+b);
                                }
                                break;
                            }
                            case 3:
                            {
                                int pin=0,apin=0,b=0,w=0,d=0;
                                String n="";
                                System.out.println("Enter PIN NUMBER:");
                                pin=scanner.nextInt();
                                query="select * from bankingdetails where AccountNumber="+an+"";
                                ResultSet rs=stat.executeQuery(query);
                                while(rs.next())
                                {
                                    n=rs.getString("FirstName");
                                    apin=rs.getInt("ATM_PIN");
                                    b=rs.getInt("Balance");
                                    w=rs.getInt("Latest_Withdrawn_Amount");
                                    d=rs.getInt("Latest_Deposit_Amount");

                                }
                                if(pin!=apin)
                                    System.out.println("Incorrect PIN!!!");
                                else
                                {
                                    System.out.println("\n\nName:"+n+"\nCurrent Balance="+b+"\n\nLatest Withdrawn Amount:"+w+"\nLatest Deposited Amount:"+d);


                                }
                                break;

                            }
                            case 4:

                                break;

                            default:
                                System.out.println("Invalid Input!");
                        }
                    }while(choice<4);
                }
                else
                {
                    hasNext=RS.next();
                    if(!hasNext)
                        System.out.println("Incorrect ACCOUNT NUMBER!!!");
                }
            }

        }

}
