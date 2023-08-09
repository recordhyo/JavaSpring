package org.example;

public class AlocalB {
    public void uselocalB(int arg) {
        int var = 1;
        class localB{
            String instancefield = "인스턴스 필드";
            static String staticfield = "정적 필드";
            localB() {
                System.out.println("localB 생성자 실행");
            }
            void instancemethod() {
                System.out.println("인스턴스 메소드 실행");
                //arg = 5;
                System.out.println(arg + " 로컬 변수 수정 불가능");
                //var = 3;
                System.out.println(var + " 로컬 변수 수정 불가능");
            }

            static void staticmethod() {
                System.out.println("정적 메소드 실행");
            }
        }

        localB b = new localB();

        System.out.println(b.instancefield);
        b.instancemethod();
        System.out.println(localB.staticfield);
        localB.staticmethod();


    }
}
