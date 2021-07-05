package designpattern.factory.abstract1;

/**
 * 小米手机实现手机产品接口
 */
public class XiaomiPhone implements IPhoneProduct{
    @Override
    public void call() {
        System.out.println("小米手机打电话");
    }
}
