package org.example;

import javax.naming.InsufficientResourcesException;

public class AccountExample {
    public static void main(String[] args){
        Account account = new Account();

        account.deposit(10000);
        System.out.println("잔액: " +account.getBalance()+"원");

        try {
            account.withdraw(30000);
        } catch (InsufficientException e) {
            String message = e.getMessage();
            System.out.println(message);
        }
    }
}
