package designpattern.factory.abstract1;

/**
 * 苹果手机实现手机产品接口
 */
public class AppleiPhone implements IPhoneProduct{
    @Override
    public void call() {
        System.out.println("苹果手机打电话");
    }
}
