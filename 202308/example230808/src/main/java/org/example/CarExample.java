package org.example;

public class CarExample {
    public static void main(String[] args){
        Car myCar = new Car();

        myCar.run();
        System.out.println();

        myCar.tire1 = new Tire2();
        myCar.tire2 = new Tire1();

        myCar.run();
    }
}
