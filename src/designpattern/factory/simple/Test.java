package designpattern.factory.simple;

public class Test {
    public static void main(String[] args) {
        //需要知道所有实现
//        Car car1=new Tesla();
//        Car car2=new WuLing();

        //使用工厂
        Car car1=CarFactory.getCar("五菱");
        Car car2=CarFactory.getCar("特斯拉");
        car1.name();
        car2.name();

    }
}
