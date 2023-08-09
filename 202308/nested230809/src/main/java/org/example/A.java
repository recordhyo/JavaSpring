package org.example;

public class A {
    A() {
        System.out.println("A 생성자 실행");
    }
    static class staticB {
        String instancefield = "인스턴스 필드";
        static String staticfield = "정적 필드";

        staticB() {
            System.out.println("B 생성자 실행");
        }

        void instancemethod() {
            System.out.println("인스턴스 메소드 실행");
        }

        static void staticmethod() {
            System.out.println("정적 메소드 실행");
        }
    }
}
