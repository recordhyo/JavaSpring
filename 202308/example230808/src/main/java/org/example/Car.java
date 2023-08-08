package org.example;

public class Car {
    Tire tire1 = new Tire1();
    Tire tire2 = new Tire2();

    void run() {
        tire1.roll();
        tire2.roll();
    }
}
