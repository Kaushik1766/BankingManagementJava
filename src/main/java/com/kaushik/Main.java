package com.kaushik;

import java.util.Scanner;

import com.kaushik.service.Management;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Management mgmt = new Management(sc);
        while (true) {
            if (mgmt.getEmployee() == null && mgmt.getCustomer() == null) {
                System.out.println("1. to create a customer");
                System.out.println("2. to create an employee");
                System.out.println("3. to login as an employee");
                System.out.println("4. to login as a customer");
                System.out.println("5. to exit");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        mgmt.createCustomer();
                        break;
                    case 2:
                        mgmt.createEmployee();
                        break;
                    case 3:
                        mgmt.employeeLogin();
                        break;
                    case 4:
                        mgmt.customerLogin();
                        break;
                    case 5:
                        sc.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } else if (mgmt.getCustomer() != null) {
                System.out.println("1. to add money");
                System.out.println("2. to send money");
                System.out.println("3. to view account details");
                System.out.println("4. to logout");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        mgmt.addMoney();
                        break;
                    case 2:
                        mgmt.sendMoney();
                        break;
                    case 3:
                        mgmt.viewAccountDetails();
                        break;
                    case 6:
                        mgmt.logout();
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } else {
                System.out.println("1. to view customer details");
                System.out.println("2. to logout");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        mgmt.viewCustomerDetails();
                        break;
                    case 2:
                        mgmt.logout();
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }
}