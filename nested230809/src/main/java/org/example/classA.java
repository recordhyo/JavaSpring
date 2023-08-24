package org.example;

public class classA {
    classA() {
        System.out.println("A 생성자 실행");
    }
    class classB {
        String instancefield = "인스턴스 필드";
        static String staticfield = "정적 필드";

        classB() {
            System.out.println("B 생성자 실행");
        }

        void instancemethod() {
            System.out.println("인스턴스 메소드 실행");
        }

        static void staticmethod() {
            System.out.println("정적 메소드 실행");
        }
    }

    void useclassB() {
        classB b = new classB();
        System.out.println(b.instancefield);
        b.instancemethod();

        System.out.println(classB.staticfield);
        classB.staticmethod();
    }

}
