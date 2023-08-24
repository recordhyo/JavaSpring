package org.example;

public class TryWithResourceExample {
    public static void main(String[] args){
        try (MyResource res = new MyResource("첫번째")) {
            String data = res.read1();
            int value = Integer.parseInt(data);
        } catch (Exception e){
            System.out.println("예외처리 : "+e.getMessage());
        }

        System.out.println();

        try (MyResource res = new MyResource("두번째")) {
            String data = res.read2();
            int value = Integer.parseInt(data);
        } catch (Exception e){
            System.out.println("예외처리 : "+e.getMessage());
        }
        System.out.println();

        MyResource res1 = new MyResource("세번째");
        MyResource res2 = new MyResource("네번째");

        try (res1; res2) {
            String data1 = res1.read1();
            String data2 = res2.read2();
            int value1 = Integer.parseInt(data1);
            int value2 = Integer.parseInt(data2);
        } catch (Exception e) {
            System.out.println("예외처리 : "+e.getMessage());
        }
    }
}
