package org.example;

public interface Service {
    default void defaultMethod(){
        System.out.println("default method 종속 코드");
        privateMethod();
        staticPrivateMethod();
    }

    private void privateMethod(){
        System.out.println("private method 종속 코드");
    }

    static void staticMethod(){
        System.out.println("static method 종속 코드");
        staticPrivateMethod();
    }

    private static void staticPrivateMethod(){
        System.out.println("private static method 종속 코드");
    }
}
