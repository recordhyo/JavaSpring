package org.example;

public class Car {
    private Tire tire = new Tire();

    private Tire tire1 = new Tire() {
        @Override
        public void roll() {
            System.out.println("익명 자식 객체1");
        }
    };

    public void run1() {
        tire.roll();
        tire1.roll();
    }

    public void run2() {
        Tire tire2 = new Tire() {
            @Override
            public void roll() {
                System.out.println("익명 자식 객체2");
            }
        };
        tire2.roll();
    }

    public void run3(Tire tire) {
        tire.roll();
    }

}
