package designpattern.factory.simple;

public class CarFactory {
    public static Car getCar(String car) {
        if (car.equals("五菱")) {
            return new WuLing();
        } else if (car.equals("特斯拉")) {
            return new Tesla();
        }
        return null;
    }
}
