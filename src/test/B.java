package test;

public class B {
    public static void main(String[] args) {
        A a=new A();
        a.setS("m");
        A s1=a;
        A s2=a;
        System.out.println(s1.getS());
        System.out.println(s2.getS());
        s1.setS("n");
        System.out.println(s1.getS());
        System.out.println(s2.getS());
//        System.out.println("\uD83C\uDEFF");

    }
}
