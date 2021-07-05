package designpattern.factory.method;

import designpattern.factory.method.CarFactory;

public class WuLingFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new WuLing();
    }
}
