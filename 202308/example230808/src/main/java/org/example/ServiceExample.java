package org.example;

public class ServiceExample {
    public static void main(String[] args) {
        Service service = new ServiceImpl();

        service.defaultMethod();
        Service.staticMethod();
    }
}
