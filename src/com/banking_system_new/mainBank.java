package com.banking_system_new;
import java.util.Scanner;
public class mainBank {
    public static void main(String[] args) throws Exception
    {
        int choice;
        Scanner scanner= new Scanner(System.in);
        do
        {
            System.out.println("************WELCOME TO THE BANKING SYSTEM**********");
            System.out.println("What service would you like to use? \t\t\t 1. Create Account \t\t\t 2. Deposit Money\t\t\t 3.ATM Work");
            choice = scanner.nextInt();
            switch (choice)
            {
                case 1:
                    accountingWork accwork= new accountingWork();
                    accwork.createAccount();
                    break;
                case 2:
                    accountingWork deposit= new accountingWork();
                    deposit.Deposit();
                    break;
                case 3:
                    atmWork atm= new atmWork();
                    atm.atmWorking();
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }while(choice<6);
    }
}
