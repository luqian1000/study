package designpattern.factory.method;

public class Test {
    public static void main(String[] args) {
        //需要知道所有实现
//        Car car1=new Tesla();
//        Car car2=new WuLing();

        //使用简单工厂
//        Car car1= CarFactory.getCar("五菱");
//        Car car2= CarFactory.getCar("特斯拉");
        //使用工厂方法
        Car car1= new TeslaFactory().getCar();
        Car car2= new WuLingFactory().getCar();
        car1.name();
        car2.name();

    }
}
