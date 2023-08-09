package org.example;

public class HomeExample {
    public static void main(String[] args){
        Home home = new Home();

        home.use1();
        home.use2();
        home.use3(new RemoteControl() {
            @Override
            public void turnOn() {
                System.out.println("익명 구현 객체 3");
            }
        });
    }
}
