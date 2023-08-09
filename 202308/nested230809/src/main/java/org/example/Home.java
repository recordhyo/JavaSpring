package org.example;

public class Home {
    private RemoteControl rc1 = new RemoteControl() {
        @Override
        public void turnOn() {
            System.out.println("익명 구현 객체 1");
        }
    };

    public void use1(){
        rc1.turnOn();
    }

    public void use2(){
        RemoteControl rc2 = new RemoteControl() {
            @Override
            public void turnOn() {
                System.out.println("익명 구현 객체 2");
            }
        };
        rc2.turnOn();
    }
    public void use3(RemoteControl rc3) {
        rc3.turnOn();
    }
}
