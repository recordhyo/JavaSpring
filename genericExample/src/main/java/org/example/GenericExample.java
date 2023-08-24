package org.example;

public class GenericExample {
    public static void main(String[] args) {
        Box<String> box1 = new Box<>();
        box1.content = "문자열타입 박스";
        String s = box1.content;
        System.out.println(s);

        Box<Integer> box2 = new Box<Integer>();
        box2.content = 12345;
        int num = box2.content;
        System.out.println(num);

    }
}
